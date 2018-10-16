package com.rs.deliveries.presentation.screens.details.di

import com.rs.deliveries.domain.UseCaseModule
import com.rs.deliveries.domain.usecases.RetrieveDeliveryItemFromCacheUseCase
import com.rs.deliveries.presentation.screens.details.DeliveryDetailsPresenter
import com.rs.deliveries.presentation.screens.details.DeliveryDetailsViewController
import dagger.Module
import dagger.Provides
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@Module(includes = [UseCaseModule::class])
class DeliveryItemPresentationModule {

    @Provides
    fun providePresenter(retrieveDeliveryItemFromCacheUseCase: RetrieveDeliveryItemFromCacheUseCase): DeliveryDetailsPresenter {
        return DeliveryDetailsPresenter(Schedulers.io(), AndroidSchedulers.mainThread(), retrieveDeliveryItemFromCacheUseCase)
    }

    @Provides
    fun provideViewController(): DeliveryDetailsViewController = DeliveryDetailsViewController()
}
