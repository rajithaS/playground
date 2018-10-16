package com.rs.deliveries.presentation.screens.list.mapper

import rx.functions.Func1

typealias Domain = com.rs.deliveries.domain.entities.Delivery
typealias Presentation = com.rs.deliveries.presentation.screens.list.model.Delivery

class DomainToPresentationMapper : Func1<List<Domain>, List<Presentation>> {

    override fun call(t: List<Domain>): List<Presentation> = map(t)

    private fun map(domainEntities: List<Domain>): List<Presentation> {
        return domainEntities.asSequence().map {
            Presentation(
                    id = it.id,
                    description = it.description,
                    imageUrl = it.imageUrl
            )
        }.toList()
    }
}
