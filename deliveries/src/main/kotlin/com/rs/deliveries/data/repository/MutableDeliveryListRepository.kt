package com.rs.deliveries.data.repository

import com.rs.deliveries.data.pojo.Delivery

interface MutableDeliveryListRepository {
    fun updateDeliveries(deliveries: List<Delivery>)
}
