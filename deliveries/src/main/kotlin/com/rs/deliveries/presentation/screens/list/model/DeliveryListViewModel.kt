package com.rs.deliveries.presentation.screens.list.model

data class DeliveryListViewModel(
        val currentOffset: Int = 0,
        val deliveries: List<Delivery> = emptyList())