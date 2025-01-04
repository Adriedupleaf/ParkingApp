package com.example.app3.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.app3.databinding.ParkingItemBinding
import com.example.domain.models.CDParkingSpot

class ParkingSpotItem @JvmOverloads constructor(
    context: Context,
    attachToParent: Boolean = false
) : RelativeLayout(context) {

    var binding = ParkingItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        attachToParent
    )

    @SuppressLint("SetTextI18n")
    fun bind(item: CDParkingSpot) {
        with(binding){
            titleLabel.text = item.name
            parkingTypeLabel.text = when(item.type) {
                "type1" -> "On Spot Parking"
                "type2" -> "Parking Lot"
                else -> "Custom"
            }
            availabilityLabel.text =  "${item.availability} Car Spots"
            distanceLabel.text = "${item.distance} km away"
            priceLabel.text = "${item.priceWeekday1} $"
            pricePeriodLabel.text = "for hr"
        }
    }
}