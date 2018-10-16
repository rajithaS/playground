package com.rs.deliveries.data.repository

import com.rs.deliveries.data.pojo.Delivery
import rx.Single

interface ImmutableDeliveryListRepository {

    fun fetchDeliveryList(offset: Int, limit: Int): Single<List<Delivery>>
}