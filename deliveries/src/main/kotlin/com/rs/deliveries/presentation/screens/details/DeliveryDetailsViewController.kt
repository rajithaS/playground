package com.rs.deliveries.presentation.screens.details

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rs.deliveries.R
import com.rs.deliveries.presentation.screens.details.model.DeliveryDetailsViewModel
import com.rs.imageloader.ImageView
import com.rs.map.GoogleMapViewProvider
import com.rs.map.MapProvider


class DeliveryDetailsViewController {

    private lateinit var mapContainer: FrameLayout
    private lateinit var image: ImageView
    private lateinit var description: TextView
    private lateinit var mapProvider: MapProvider<*>
    private lateinit var googleMap: GoogleMap

    private var mapView: MapView? = null

    fun init(activity: DeliveryDetailsActivity, bundle: Bundle?) {
        mapContainer = activity.findViewById(R.id.mapContainer)
        image = activity.findViewById(R.id.image)
        description = activity.findViewById(R.id.description)
        setupMap(activity, bundle)
    }

    private fun setupMap(activity: DeliveryDetailsActivity, bundle: Bundle?) {
        mapProvider = GoogleMapViewProvider(activity).also {
            mapView = it.getView()
            mapContainer.addView(mapView)
        }

        mapView?.run {
            onCreate(bundle)
            getMapAsync {
                googleMap = it
                googleMap.setMinZoomPreference(12f)
                activity.onDetailsViewReady()
            }
        }
    }

    fun setData(delivery: DeliveryDetailsViewModel) {
        image.setImageURI(delivery.imageUrl)
        description.text = delivery.description

        if (::googleMap.isInitialized) {
            val location = LatLng(delivery.latitude, delivery.longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(delivery.address))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))

        }
    }

    fun onStart() {
        mapView?.onStart()
    }

    fun onDestroy() {
        mapView?.onDestroy()
    }

    fun onResume() {
        mapView?.onResume()
    }

    fun onPause() {
        mapView?.onPause()
    }

    fun onStop() {
        mapView?.onStop()
    }

    fun onSaveInstanceState(outState: Bundle?) {
        mapView?.onSaveInstanceState(outState)
    }

    fun onLowMemory() {
        mapView?.onLowMemory()
    }
}
