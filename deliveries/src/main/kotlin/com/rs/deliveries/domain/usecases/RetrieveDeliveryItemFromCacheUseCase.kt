package com.rs.deliveries.domain.usecases

import com.rs.deliveries.data.db.DeliveryDatabase
import com.rs.deliveries.data.pojo.Location
import com.rs.deliveries.domain.entities.Delivery
import rx.Single

class RetrieveDeliveryItemFromCacheUseCase(private val deliveryDatabase: DeliveryDatabase) {

    fun retrieveDelivery(id: Int): Single<Delivery> {
        return Single.fromCallable { deliveryDatabase.dataAccessObject().fetchDeliverItem(id) }
                .map {
                    Delivery(
                            id = it.id,
                            description = it.description,
                            imageUrl = it.imageUrl,
                            location = Location(
                                    lat = it.latitude,
                                    lng = it.longitude,
                                    address = it.address
                            )
                    )
                }
    }
}
