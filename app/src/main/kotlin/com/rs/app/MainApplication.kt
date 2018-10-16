package com.rs.app

import android.app.Activity
import android.app.Application
import com.rs.app.di.AndroidModule
import com.rs.app.di.DaggerApplicationComponent
import com.rs.imageloader.ImageLoader
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        inject()
        imageLoader.initialize(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    private fun inject() {
        DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
                .inject(this)
    }
}
