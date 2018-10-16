package com.rs.deliveries.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Delivery(
        @PrimaryKey val id: Int,
        val description: String,
        val imageUrl: String,
        val latitude: Double,
        val longitude: Double,
        val address: String)