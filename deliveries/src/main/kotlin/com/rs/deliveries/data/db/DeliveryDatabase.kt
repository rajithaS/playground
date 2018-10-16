package com.rs.deliveries.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Delivery::class], version = 1, exportSchema = false)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract fun dataAccessObject(): DeliveryDAO
}
