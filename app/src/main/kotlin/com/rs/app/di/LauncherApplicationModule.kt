package com.rs.app.di

import android.app.Activity
import com.rs.app.launcher.LauncherActivity
import com.rs.app.launcher.di.LauncherActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [LauncherActivityComponent::class])
abstract class LauncherApplicationModule {

    @Binds
    @IntoMap
    @ActivityKey(LauncherActivity::class)
    internal abstract fun bindActivityInjectorFactory(builder: LauncherActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}
