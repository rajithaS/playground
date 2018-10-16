package com.rs.app.launcher.di

import com.rs.app.launcher.LauncherActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [LauncherPresentationModule::class])
interface LauncherActivityComponent : AndroidInjector<LauncherActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LauncherActivity>()
}
