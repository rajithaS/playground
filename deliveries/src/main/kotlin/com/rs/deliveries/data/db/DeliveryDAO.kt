package com.rs.deliveries.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface DeliveryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeliveries(deliveries: List<Delivery>)

    @Query("SELECT * FROM Delivery")
    fun fetchAllDeliveries(): List<Delivery>

    @Query("SELECT * FROM Delivery WHERE id=:id LIMIT 1")
    fun fetchDeliverItem(id: Int) : Delivery
}
