package com.rs.deliveries.data.api

import com.rs.deliveries.data.pojo.Delivery
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface DeliveryApi {

    @GET("/deliveries")
    fun deliveries(@Query("offset") offset: String, @Query("limit") limit: String): Single<List<Delivery>>
}