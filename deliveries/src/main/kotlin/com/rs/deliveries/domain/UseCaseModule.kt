package com.rs.deliveries.domain

import com.rs.deliveries.data.DataModule
import com.rs.deliveries.data.db.DeliveryDatabase
import com.rs.deliveries.data.repository.DeliveryListCachedRepository
import com.rs.deliveries.data.repository.DeliveryListNetworkRepository
import com.rs.deliveries.domain.usecases.RetrieveCachedListOfDeliveryUseCase
import com.rs.deliveries.domain.usecases.RetrieveDeliveryItemFromCacheUseCase
import com.rs.deliveries.domain.usecases.RetrieveNetworkListOfDeliveriesUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class UseCaseModule {

    @Provides
    fun provideDeliveryListFetchUseCase(
            deliveryListNetworkRepository: DeliveryListNetworkRepository,
            deliveryListCachedRepository: DeliveryListCachedRepository): RetrieveNetworkListOfDeliveriesUseCase {

        return RetrieveNetworkListOfDeliveriesUseCase(deliveryListNetworkRepository, deliveryListCachedRepository)
    }

    @Provides
    fun provideCachedDeliveryListFetchUseCase(
            deliveryListCachedRepository: DeliveryListCachedRepository): RetrieveCachedListOfDeliveryUseCase {
        return RetrieveCachedListOfDeliveryUseCase(deliveryListCachedRepository)
    }

    @Provides
    fun provideRetrieveItemFromCache(deliveryDatabase: DeliveryDatabase): RetrieveDeliveryItemFromCacheUseCase {
        return RetrieveDeliveryItemFromCacheUseCase(deliveryDatabase)
    }
}
