package com.example.app3.ui.home.bottomsheetAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.app3.databinding.WeekdaysItemBinding
import com.example.domain.models.CDBottomsheetAdapterItem

class BottomsheetItem @JvmOverloads constructor(
    context: Context,
    attachToParent: Boolean = false
) : RelativeLayout(context) {

    var binding = WeekdaysItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        attachToParent
    )

    @SuppressLint("SetTextI18n")
    fun bind(item: CDBottomsheetAdapterItem) {
        with(binding){
            titleLabel.text = item.period
            priceLabel.text = "${item.price} $"
        }
    }
}