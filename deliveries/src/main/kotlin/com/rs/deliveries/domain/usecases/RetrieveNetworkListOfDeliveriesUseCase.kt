package com.rs.deliveries.domain.usecases

import android.util.Log
import com.rs.deliveries.data.repository.DeliveryListCachedRepository
import com.rs.deliveries.data.repository.DeliveryListNetworkRepository
import com.rs.deliveries.domain.entities.Delivery
import com.rs.deliveries.domain.usecases.mapper.DataToDomainMapper
import rx.Single
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RetrieveNetworkListOfDeliveriesUseCase(
        private val deliveryListNetworkRepository: DeliveryListNetworkRepository,
        private val deliveryListCachedRepository: DeliveryListCachedRepository) : RetrieveListOfDeliveriesToDisplayUseCase {

    private val timeoutSeconds = 20L

    override fun retrieveDeliveries(offset: Int, limit: Int): Single<List<Delivery>> {
        return deliveryListNetworkRepository
                .fetchDeliveryList(offset, limit)
                .timeout(timeoutSeconds, TimeUnit.SECONDS, Schedulers.computation())
                .doOnSuccess { deliveryListCachedRepository.updateDeliveries(it) }
                .doOnError { e -> Log.e("RetrieveListNetwork", e.message) }
                .map(DataToDomainMapper())
    }
}
