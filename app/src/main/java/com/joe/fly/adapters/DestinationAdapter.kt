package com.joe.fly.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joe.fly.R
import com.joe.fly.activities.DestinationDetailActivity
import com.joe.fly.models.Destination

class DestinationAdapter(private val destinationList: List<Destination>) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DestinationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.destination = destinationList[position]
        holder.place.text = destinationList[position].city

        holder.itemView.setOnClickListener { view ->
            val context = view.context
            val intent = Intent(context, DestinationDetailActivity::class.java)
            intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, holder.destination!!.id)
            context.startActivity(intent)
        }
    }

    class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val place: TextView = itemView.findViewById(R.id.textView_destination)
        var destination: Destination? = null

        override fun toString(): String {
            return "${super.toString()} '${place.text}'"
        }
    }
}