package com.example.app3.ui.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.CDParkingSpot

class ParkingAdapter(
    private var items: List<CDParkingSpot> = emptyList(),
    private var listener: OnParkingClickListener? = null
): RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<CDParkingSpot>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        return ParkingViewHolder(
            ParkingSpotItem(parent.context)
        ).apply {
            itemView.setOnClickListener {
                val item = items[adapterPosition]
                listener?.onOpenListener(item)
            }
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ParkingViewHolder(
        var view: ParkingSpotItem
    ) :
        RecyclerView.ViewHolder(view.binding.root) {
        fun bind(cardOperationItem: CDParkingSpot) {
            view.bind(cardOperationItem)
        }
    }

    interface OnParkingClickListener{
        fun onOpenListener(parkingSpot: CDParkingSpot)
        fun onCloseListener()
    }
}