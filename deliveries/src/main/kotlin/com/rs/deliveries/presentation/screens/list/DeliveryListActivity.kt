package com.rs.deliveries.presentation.screens.list

import android.os.Bundle
import android.widget.Toast
import com.rs.deliveries.R
import com.rs.deliveries.presentation.arch.BaseMvpActivity
import com.rs.deliveries.presentation.screens.list.model.Delivery
import kotlinx.android.synthetic.main.activity_delivery_list.*
import javax.inject.Inject

class DeliveryListActivity : BaseMvpActivity<DeliveryListView, DeliveryListPresenter>(), DeliveryListView {

    @Inject
    lateinit var injectedPresenter: DeliveryListPresenter
    @Inject
    lateinit var viewController: DeliveryListViewController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)
        setupToolBarWithHomeEnabled(toolbar)
        viewController.init(this)
        injectedPresenter.load()
    }

    override fun onDestroy() {
        super.onDestroy()
        injectedPresenter.onDestroy()
    }

    override fun createPresenter(): DeliveryListPresenter = injectedPresenter
    override fun onDeliveryItemClick(delivery: Delivery) = injectedPresenter.onDeliveryItemClick(delivery)
    override fun onDeliveryListSwipedDown() = injectedPresenter.onDeliveryListSwipedDown()
    override fun appendDeliveries(deliveries: List<Delivery>) = viewController.appendDeliveries(deliveries)
    override fun refreshList() = viewController.refreshDeliveryList()

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showLowerProgress() = viewController.showLowerProgress()

    override fun hideLowerProgress() = viewController.hideLowerProgress()

    override fun showNoDataAvailable() = viewController.showNoDataAvailable()

    override fun onLoadMore(totalItemsCount: Int) = injectedPresenter.onLoadMore(totalItemsCount)
}
