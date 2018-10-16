package com.rs.deliveries.di

import android.app.Activity
import com.rs.deliveries.presentation.screens.details.DeliveryDetailsActivity
import com.rs.deliveries.presentation.screens.details.di.DeliveryItemActivityComponent
import com.rs.deliveries.presentation.screens.list.DeliveryListActivity
import com.rs.deliveries.presentation.screens.list.di.DeliveryListActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    DeliveryListActivityComponent::class,
    DeliveryItemActivityComponent::class
])
abstract class DeliveryApplicationModule {

    @Binds
    @IntoMap
    @ActivityKey(DeliveryListActivity::class)
    internal abstract fun bindListActivityInjectorFactory(builder: DeliveryListActivityComponent.Builder)
            : AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DeliveryDetailsActivity::class)
    internal abstract fun bindItemActivityInjectorFactory(builder: DeliveryItemActivityComponent.Builder)
            : AndroidInjector.Factory<out Activity>
}