package com.rs.map

import android.content.Context
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView

class GoogleMapViewProvider(private val context: Context) : MapProvider<MapView> {

    override fun getView(): MapView {
        return MapView(context, GoogleMapOptions())
    }
}
