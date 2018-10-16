package com.rs.deliveries.presentation.screens.list.di

import com.rs.deliveries.presentation.screens.list.DeliveryListActivity
import com.rs.network.NetworkModule
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [DeliveryListPresentationModule::class])
interface DeliveryListActivityComponent : AndroidInjector<DeliveryListActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveryListActivity>()
}
