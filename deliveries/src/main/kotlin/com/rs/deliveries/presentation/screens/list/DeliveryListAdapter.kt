package com.rs.deliveries.presentation.screens.list

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rs.deliveries.R
import com.rs.deliveries.presentation.screens.list.model.Delivery
import com.rs.imageloader.ImageView


class DeliveryListAdapter(
        private val deliveryList: MutableList<Delivery> = mutableListOf(),
        private val listener: (Delivery) -> Unit = {}) : RecyclerView.Adapter<DeliveryListAdapter.DeliveryItemViewHolder>() {

    class DeliveryItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView by lazy { view.findViewById<ImageView>(R.id.image) }
        val description: TextView by lazy { view.findViewById<TextView>(R.id.txtDescription) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        val deliveryListItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_delivery, parent, false)
        return DeliveryItemViewHolder(deliveryListItemView)
    }

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        val delivery = deliveryList[position]
        holder.description.text = delivery.description
        holder.image.setImageURI(delivery.imageUrl)
        holder.view.setOnClickListener { listener.invoke(delivery) }
    }

    override fun getItemCount() = deliveryList.size

    fun addDeliveries(deliveries: List<Delivery>) {
        deliveryList += deliveries
        notifyDataSetChanged()
    }

    fun clearDeliveries() {
        deliveryList.clear()
        notifyDataSetChanged()
    }
}
