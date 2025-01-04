package com.example.app3.ui.home.bottomsheetAdapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.CDBottomsheetAdapterItem

class BottomsheetAdapter(
    private var items: List<CDBottomsheetAdapterItem> = emptyList(),
    private var listener: OnTariffClickListener? = null
): RecyclerView.Adapter<BottomsheetAdapter.BottomsheetViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<CDBottomsheetAdapterItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomsheetViewHolder {
        return BottomsheetViewHolder(
            BottomsheetItem(parent.context)
        ).apply {
            itemView.setOnClickListener {
                val item = items[adapterPosition]
                listener?.onSelectedListener(item)
            }
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: BottomsheetViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class BottomsheetViewHolder(
        var view: BottomsheetItem
    ) :
        RecyclerView.ViewHolder(view.binding.root) {
        fun bind(cardOperationItem: CDBottomsheetAdapterItem) {
            view.bind(cardOperationItem)
        }
    }

    interface OnTariffClickListener{
        fun onSelectedListener(parkingSpot: CDBottomsheetAdapterItem)
    }
}