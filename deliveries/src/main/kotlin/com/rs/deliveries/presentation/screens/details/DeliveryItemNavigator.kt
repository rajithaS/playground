package com.rs.deliveries.presentation.screens.details

import android.content.Context
import android.content.Intent

class DeliveryItemNavigator(private val context: Context) {
    fun navigateTo(id: Int) {
        context.startActivity(
                Intent(context, DeliveryDetailsActivity::class.java).apply {
                    putExtra(DeliveryDetailsActivity.EXTRA_ID, id)
                })
    }
}
