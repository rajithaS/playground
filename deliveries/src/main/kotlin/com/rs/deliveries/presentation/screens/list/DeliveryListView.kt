package com.rs.deliveries.presentation.screens.list

import com.hannesdorfmann.mosby.mvp.MvpView
import com.rs.deliveries.presentation.screens.list.model.Delivery

interface DeliveryListView : MvpView {
    fun appendDeliveries(deliveries : List<Delivery>)
    fun onDeliveryItemClick(delivery : Delivery)
    fun onDeliveryListSwipedDown()
    fun refreshList()
    fun showError(error: String)
    fun showLowerProgress()
    fun hideLowerProgress()
    fun onLoadMore(totalItemsCount: Int)
    fun showNoDataAvailable()
}
