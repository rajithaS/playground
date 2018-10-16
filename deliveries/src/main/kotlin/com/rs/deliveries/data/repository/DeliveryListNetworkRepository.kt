package com.rs.deliveries.data.repository

import com.rs.deliveries.data.api.DeliveryApi
import com.rs.deliveries.data.pojo.Delivery
import rx.Single

class DeliveryListNetworkRepository(private val deliveryApi: DeliveryApi) : ImmutableDeliveryListRepository {

    override fun fetchDeliveryList(offset: Int, limit: Int): Single<List<Delivery>> {
        return deliveryApi.deliveries(offset = offset.toString(), limit = limit.toString())
    }
}

