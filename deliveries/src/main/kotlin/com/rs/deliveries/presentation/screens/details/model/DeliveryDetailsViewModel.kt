package com.rs.deliveries.presentation.screens.details.model

data class DeliveryDetailsViewModel(
        val description: String,
        val imageUrl: String,
        val latitude: Double,
        val longitude: Double,
        val address : String)