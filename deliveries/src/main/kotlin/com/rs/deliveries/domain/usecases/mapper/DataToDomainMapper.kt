package com.rs.deliveries.domain.usecases.mapper

import com.rs.deliveries.data.pojo.Location
import rx.functions.Func1


typealias Data = com.rs.deliveries.data.pojo.Delivery
typealias Domain = com.rs.deliveries.domain.entities.Delivery

class DataToDomainMapper : Func1<List<Data>, List<Domain>> {
    override fun call(t: List<Data>): List<Domain> = map(t)

    private fun map(data: List<Data>): List<Domain> {
        return data.asSequence().map {
            Domain(
                    id = it.id,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    location = Location(
                            lat = it.location.lat,
                            lng = it.location.lng,
                            address = it.location.address
                    )
            )
        }.toList()
    }

}