package com.rs.deliveries.data

import android.arch.persistence.room.Room
import android.content.Context
import com.rs.deliveries.data.api.DeliveryApi
import com.rs.deliveries.data.db.DeliveryDatabase
import com.rs.deliveries.data.repository.DeliveryListCachedRepository
import com.rs.deliveries.data.repository.DeliveryListNetworkRepository
import com.rs.network.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class DataModule {

    companion object {
        const val DATABASE_NAME = "deliveries";
    }

    @Provides
    fun provideDeliveryApi(retrofitBuilder: Retrofit): DeliveryApi {
        return retrofitBuilder.create(DeliveryApi::class.java)
    }

    @Provides
    fun deliveryDatabase(context: Context): DeliveryDatabase {
        return Room.databaseBuilder(context, DeliveryDatabase::class.java, DATABASE_NAME)
                .build()
    }

    @Provides
    fun provideDeliveryListNetworkRepository(deliveryApi: DeliveryApi): DeliveryListNetworkRepository {
        return DeliveryListNetworkRepository(deliveryApi)
    }

    @Provides
    fun provideDeliveryListCachedRepository(deliveryDatabase: DeliveryDatabase): DeliveryListCachedRepository {
        return DeliveryListCachedRepository(deliveryDatabase)
    }
}
