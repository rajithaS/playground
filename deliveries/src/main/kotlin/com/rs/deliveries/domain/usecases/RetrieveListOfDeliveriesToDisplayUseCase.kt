package com.rs.deliveries.domain.usecases

import com.rs.deliveries.domain.entities.Delivery
import rx.Single

interface RetrieveListOfDeliveriesToDisplayUseCase {
    fun retrieveDeliveries(offset: Int, limit: Int): Single<List<Delivery>>
}