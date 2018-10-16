package com.rs.deliveries.presentation.screens.details

import android.os.Bundle
import com.rs.deliveries.R
import com.rs.deliveries.presentation.arch.BaseMvpActivity
import com.rs.deliveries.presentation.screens.details.model.DeliveryDetailsViewModel
import javax.inject.Inject

class DeliveryDetailsActivity : BaseMvpActivity<DeliveryDetailsView, DeliveryDetailsPresenter>(), DeliveryDetailsView {

    companion object {
        const val EXTRA_ID = "id"
    }

    @Inject
    lateinit var injectedPresenter: DeliveryDetailsPresenter
    @Inject
    lateinit var viewController: DeliveryDetailsViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)
        setupToolBarWithHomeEnabled(findViewById(R.id.toolbar))
        viewController.init(this, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewController.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewController.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewController.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewController.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        viewController.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        viewController.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        injectedPresenter.onDestroy()
        viewController.onDestroy()
    }

    override fun showDeliveryItem(deliveryDetailsViewModel: DeliveryDetailsViewModel) {
        viewController.setData(deliveryDetailsViewModel)
    }

    override fun createPresenter(): DeliveryDetailsPresenter = injectedPresenter

    override fun onDetailsViewReady() {
        intent.extras
                ?.getInt(EXTRA_ID, -1)
                ?.takeIf { it != -1 }
                ?.let { injectedPresenter.loadDeliveryItem(it) }
                ?: finish()
    }
}
