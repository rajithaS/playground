package com.rs.app.launcher.di

import android.content.Context
import com.rs.deliveries.presentation.screens.list.DeliveryListNavigator
import dagger.Module
import dagger.Provides

@Module
class LauncherPresentationModule {

    @Provides
    fun provideDeliveryNavigator(context: Context): DeliveryListNavigator = DeliveryListNavigator(context)
}
