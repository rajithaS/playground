package com.rs.deliveries.presentation.screens.list

import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.rs.deliveries.R
import com.rs.deliveries.presentation.screens.list.model.Delivery
import com.rs.deliveries.presentation.support.EndlessRecyclerViewScrollListener

class DeliveryListViewController {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: DeliveryListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var noDataView: TextView

    fun init(activity: DeliveryListActivity) {
        swipeLayout = activity.findViewById(R.id.swipeLayout)
        swipeLayout.setOnRefreshListener { activity.onDeliveryListSwipedDown() }
        progressBar = activity.findViewById(R.id.progress)
        noDataView = activity.findViewById(R.id.noDataView)
        linearLayoutManager = LinearLayoutManager(activity)
        viewAdapter = DeliveryListAdapter { activity.onDeliveryItemClick(it) }
        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                activity.onLoadMore(totalItemsCount)
            }
        }

        recyclerView = activity.findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = viewAdapter
            addOnScrollListener(scrollListener)
        }
    }

    fun appendDeliveries(deliveries: List<Delivery>) {
        noDataView.visibility = GONE
        swipeLayout.isRefreshing = false
        progressBar.hide()
        viewAdapter.addDeliveries(deliveries)
    }

    fun refreshDeliveryList() {
        viewAdapter.clearDeliveries()
        swipeLayout.isRefreshing = true
        scrollListener.resetState()
    }

    fun showLowerProgress() {
        swipeLayout.isRefreshing = false
        progressBar.show()
    }

    fun hideLowerProgress() {
        progressBar.hide()
        scrollListener.resetState()
    }

    fun showNoDataAvailable() {
        noDataView.visibility = VISIBLE
        swipeLayout.isRefreshing = false
        progressBar.hide()
    }
}
