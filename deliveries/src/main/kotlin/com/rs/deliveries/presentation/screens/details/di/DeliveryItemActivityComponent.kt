package com.rs.deliveries.presentation.screens.details.di

import com.rs.deliveries.presentation.screens.details.DeliveryDetailsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [DeliveryItemPresentationModule::class])
interface DeliveryItemActivityComponent : AndroidInjector<DeliveryDetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveryDetailsActivity>()
}