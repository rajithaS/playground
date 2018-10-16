package com.rs.deliveries.presentation.screens.list

import android.content.Context
import android.content.Intent

class DeliveryListNavigator(private val context: Context) {
    fun navigateTo() = context.startActivity(Intent(context, DeliveryListActivity::class.java))
}
