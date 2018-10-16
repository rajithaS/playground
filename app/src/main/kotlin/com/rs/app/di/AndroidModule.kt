package com.rs.app.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: Context) {

    @Provides
    fun provideApplicationContext(): Context = context

}