package com.rs.deliveries.domain.entities

import com.rs.deliveries.data.pojo.Location

data class Delivery(val id: Int, val description: String, val imageUrl: String, val location: Location)