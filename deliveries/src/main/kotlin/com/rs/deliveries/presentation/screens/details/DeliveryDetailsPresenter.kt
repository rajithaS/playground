package com.rs.deliveries.presentation.screens.details

import android.util.Log
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.rs.deliveries.domain.entities.Delivery
import com.rs.deliveries.domain.usecases.RetrieveDeliveryItemFromCacheUseCase
import com.rs.deliveries.presentation.arch.ifViewAttached
import com.rs.deliveries.presentation.screens.details.model.DeliveryDetailsViewModel
import rx.Scheduler
import rx.subscriptions.CompositeSubscription

class DeliveryDetailsPresenter(
        private val ioSchedulers: Scheduler,
        private val mainScheduler: Scheduler,
        private val retrieveDeliveryItemFromCacheUseCase: RetrieveDeliveryItemFromCacheUseCase)
    : MvpBasePresenter<DeliveryDetailsView>() {

    private val compositeSubscription = CompositeSubscription()
    private lateinit var deliveryDetailsViewModel: DeliveryDetailsViewModel

    fun loadDeliveryItem(id: Int) {
        compositeSubscription.add(
                retrieveDeliveryItemFromCacheUseCase
                        .retrieveDelivery(id)
                        .subscribeOn(ioSchedulers)
                        .observeOn(mainScheduler)
                        .subscribe(this::onDeliveryItemLoad, this::onError))
    }

    fun onDestroy() {
        compositeSubscription.unsubscribe()
    }

    private fun onDeliveryItemLoad(deliveryItem: Delivery) {
        deliveryDetailsViewModel = DeliveryDetailsViewModel(
                description = deliveryItem.description,
                imageUrl = deliveryItem.imageUrl,
                latitude = deliveryItem.location.lat,
                longitude = deliveryItem.location.lng,
                address = deliveryItem.location.address
        )
        ifViewAttached { it.showDeliveryItem(deliveryDetailsViewModel) }
    }

    private fun onError(throwable: Throwable) {
        Log.e("details", throwable.message)
    }
}
