package com.rs.app.di

import com.rs.app.MainApplication
import com.rs.deliveries.di.DeliveryApplicationModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    ImageLoaderModule::class,
    DeliveryApplicationModule::class,
    LauncherApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MainApplication)

}
