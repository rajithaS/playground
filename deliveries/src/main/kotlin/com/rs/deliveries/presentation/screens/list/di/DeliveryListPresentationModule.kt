package com.rs.deliveries.presentation.screens.list.di

import android.content.Context
import com.rs.deliveries.domain.UseCaseModule
import com.rs.deliveries.domain.usecases.RetrieveCachedListOfDeliveryUseCase
import com.rs.deliveries.domain.usecases.RetrieveNetworkListOfDeliveriesUseCase
import com.rs.deliveries.presentation.screens.details.DeliveryItemNavigator
import com.rs.deliveries.presentation.screens.list.DeliveryListPresenter
import com.rs.deliveries.presentation.screens.list.DeliveryListViewController
import com.rs.network.NetworkInspector
import dagger.Module
import dagger.Provides
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Module(includes = [UseCaseModule::class])
class DeliveryListPresentationModule {

    @Provides
    fun provideViewController(): DeliveryListViewController = DeliveryListViewController()

    @Provides
    fun providePresenter(
            deliveryItemNavigator: DeliveryItemNavigator,
            networkInspector: NetworkInspector,
            retrieveCachedListOfDeliveryUseCase: RetrieveCachedListOfDeliveryUseCase,
            retrieveListOfDeliveriesToDisplayUseCase: RetrieveNetworkListOfDeliveriesUseCase): DeliveryListPresenter {
        return DeliveryListPresenter(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                networkInspector,
                retrieveListOfDeliveriesToDisplayUseCase,
                retrieveCachedListOfDeliveryUseCase,
                deliveryItemNavigator)
    }

    @Provides
    fun provideDeliveryItemNavigator(context: Context): DeliveryItemNavigator = DeliveryItemNavigator(context)
}
