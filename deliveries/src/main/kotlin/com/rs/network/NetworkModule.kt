package com.rs.network

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Configuration.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        }.build()
    }

    @Provides
    fun provideNetworkInspector(context: Context): NetworkInspector = NetworkInspector(context)
}
