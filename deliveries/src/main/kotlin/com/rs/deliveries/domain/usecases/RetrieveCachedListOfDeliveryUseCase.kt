package com.rs.deliveries.domain.usecases

import android.util.Log
import com.rs.deliveries.data.repository.DeliveryListCachedRepository
import com.rs.deliveries.domain.entities.Delivery
import com.rs.deliveries.domain.usecases.mapper.DataToDomainMapper
import rx.Single

class RetrieveCachedListOfDeliveryUseCase(private val cachedRepository: DeliveryListCachedRepository) : RetrieveListOfDeliveriesToDisplayUseCase {

    override fun retrieveDeliveries(offset: Int, limit: Int): Single<List<Delivery>> {
        return cachedRepository
                .fetchDeliveryList(offset, limit)
                .map(DataToDomainMapper())
                .doOnError { e -> Log.e("RetrieveListCache", e.message) }
    }
}
