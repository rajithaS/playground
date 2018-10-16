package com.rs.deliveries.data.repository

import com.rs.deliveries.data.db.DeliveryDatabase
import com.rs.deliveries.data.pojo.Delivery
import com.rs.deliveries.data.repository.mapper.DBEntityToDataMapper
import com.rs.deliveries.data.repository.mapper.DataToDBEntityMapper
import rx.Single

class DeliveryListCachedRepository(private val deliveryDatabase: DeliveryDatabase)
    : ImmutableDeliveryListRepository, MutableDeliveryListRepository {

    override fun updateDeliveries(deliveries: List<Delivery>) {
        deliveryDatabase.dataAccessObject().insertDeliveries(DataToDBEntityMapper().call(deliveries))
    }

    override fun fetchDeliveryList(offset: Int, limit: Int): Single<List<Delivery>> {
        return Single
                .fromCallable { deliveryDatabase.dataAccessObject().fetchAllDeliveries() }
                .map(DBEntityToDataMapper())
    }
}
