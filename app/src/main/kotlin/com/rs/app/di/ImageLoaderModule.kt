package com.rs.app.di

import com.rs.imageloader.FrescoImageLoader
import com.rs.imageloader.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule {
    @Provides
    fun provideImageLoader(): ImageLoader = FrescoImageLoader()
}
