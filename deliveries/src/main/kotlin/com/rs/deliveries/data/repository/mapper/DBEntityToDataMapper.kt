package com.rs.deliveries.data.repository.mapper

import com.rs.deliveries.data.pojo.Location
import rx.functions.Func1

typealias Data = com.rs.deliveries.data.pojo.Delivery
typealias DB = com.rs.deliveries.data.db.Delivery

class DBEntityToDataMapper : Func1<List<DB>, List<Data>> {

    override fun call(t: List<DB>): List<Data> = map(t)

    private fun map(entities: List<DB>): List<Data> {
        return entities.asSequence().map {
            Data(
                    id = it.id,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    location = Location(
                            lat = it.latitude,
                            lng = it.longitude,
                            address = it.address

                    )
            )
        }.toList()
    }
}

class DataToDBEntityMapper : Func1<List<Data>, List<DB>> {
    override fun call(t: List<Data>): List<DB> = map(t)
    private fun map(data: List<Data>): List<DB> {
        return data.asSequence().map {
            DB(
                    id = it.id,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    latitude = it.location.lat,
                    longitude = it.location.lng,
                    address = it.location.address)
        }.toList()
    }
}
