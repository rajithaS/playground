package com.rs.deliveries.presentation.screens.list

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.rs.deliveries.domain.usecases.RetrieveCachedListOfDeliveryUseCase
import com.rs.deliveries.domain.usecases.RetrieveNetworkListOfDeliveriesUseCase
import com.rs.deliveries.presentation.arch.ifViewAttached
import com.rs.deliveries.presentation.screens.details.DeliveryItemNavigator
import com.rs.deliveries.presentation.screens.list.mapper.DomainToPresentationMapper
import com.rs.deliveries.presentation.screens.list.model.Delivery
import com.rs.deliveries.presentation.screens.list.model.DeliveryListViewModel
import com.rs.network.NetworkInspector
import rx.Scheduler
import rx.subscriptions.CompositeSubscription

class DeliveryListPresenter(
        private val ioSchedulers: Scheduler,
        private val mainScheduler: Scheduler,
        private val networkInspector: NetworkInspector,
        private val retrieveListOfDeliveriesToDisplayUseCase: RetrieveNetworkListOfDeliveriesUseCase,
        private val retrieveCachedListOfDeliveryUseCase: RetrieveCachedListOfDeliveryUseCase,
        private val deliveryItemNavigator: DeliveryItemNavigator)
    : MvpBasePresenter<DeliveryListView>() {

    companion object {
        const val LIMIT = 20
    }

    private var deliveryListViewModel = DeliveryListViewModel()
    private val compositeSubscription = CompositeSubscription()

    fun load() {
        ifViewAttached { view ->
            if (networkInspector.isConnected()) {
                loadFromNetwork(deliveryListViewModel.currentOffset, LIMIT)
            } else {
                loadFromCache()
                view.showError("Please check your internet connection...")
            }
        }
    }

    private fun loadFromNetwork(offset: Int, limit: Int) {
        compositeSubscription.add(
                retrieveListOfDeliveriesToDisplayUseCase.retrieveDeliveries(offset, limit)
                        .map(DomainToPresentationMapper())
                        .subscribeOn(ioSchedulers)
                        .observeOn(mainScheduler)
                        .subscribe(this::onDeliveriesLoaded) { handleNetworkLoadError() })
    }

    private fun handleNetworkLoadError() {
        ifViewAttached {
            it.hideLowerProgress()
            val isInitialCall = deliveryListViewModel.currentOffset == 0
            if (isInitialCall) {
                loadFromCache()
            }
        }
    }

    private fun loadFromCache() {
        compositeSubscription.add(
                retrieveCachedListOfDeliveryUseCase
                        .retrieveDeliveries(deliveryListViewModel.currentOffset, LIMIT)
                        .map(DomainToPresentationMapper())
                        .subscribeOn(ioSchedulers)
                        .observeOn(mainScheduler)
                        .subscribe(this::onDeliveriesLoaded) { throwable -> ifViewAttached { it.showError(throwable.message.orEmpty()) } })
    }

    private fun onDeliveriesLoaded(deliveries: List<Delivery>) {
        ifViewAttached {
            it.hideLowerProgress()
            if (deliveries.isEmpty() && deliveryListViewModel.deliveries.isEmpty()) {
                it.showNoDataAvailable()
            } else {
                deliveryListViewModel = deliveryListViewModel.copy(deliveries = deliveryListViewModel.deliveries.plus(deliveries))
                it.appendDeliveries(deliveries)
            }
        }
    }

    fun onDestroy() = compositeSubscription.unsubscribe()
    fun onDeliveryItemClick(delivery: Delivery) = deliveryItemNavigator.navigateTo(delivery.id)

    fun onDeliveryListSwipedDown() {
        ifViewAttached {
            deliveryListViewModel = deliveryListViewModel.copy(currentOffset = 0, deliveries = emptyList())
            it.refreshList()
            load()
        }
    }

    fun onLoadMore(totalItemsCount: Int) {
        ifViewAttached {
            it.showLowerProgress()
            deliveryListViewModel = deliveryListViewModel.copy(currentOffset = totalItemsCount)
            load()
        }
    }
}