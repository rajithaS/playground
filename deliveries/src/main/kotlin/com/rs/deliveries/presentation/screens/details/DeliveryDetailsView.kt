package com.rs.deliveries.presentation.screens.details

import com.hannesdorfmann.mosby.mvp.MvpView
import com.rs.deliveries.presentation.screens.details.model.DeliveryDetailsViewModel

interface DeliveryDetailsView : MvpView {
    fun showDeliveryItem(deliveryDetailsViewModel: DeliveryDetailsViewModel)
    fun onDetailsViewReady()
}