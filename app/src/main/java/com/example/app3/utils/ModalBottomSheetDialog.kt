package com.example.app3.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.app3.databinding.BottomSheetBinding
import com.example.app3.ui.home.ParkingAdapter
import com.example.app3.ui.home.bottomsheetAdapter.BottomsheetAdapter
import com.example.domain.models.CDBottomsheetAdapterItem
import com.example.domain.models.CDParkingSpot
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetDialog(private val parkingSpot: CDParkingSpot) : BottomSheetDialogFragment(), BottomsheetAdapter.OnTariffClickListener {

    private lateinit var binding: BottomSheetBinding
    private lateinit var weekdaysAdapter: BottomsheetAdapter
    private lateinit var weekendsAdapter: BottomsheetAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetBinding.inflate(
            inflater,
            container,
            false
        )
        weekdaysAdapter = BottomsheetAdapter(listener = this)
        weekendsAdapter = BottomsheetAdapter(listener = this)
        weekdaysAdapter.setData(listOf(
            CDBottomsheetAdapterItem("1hr - 2hr:", parkingSpot.priceWeekday1),
            CDBottomsheetAdapterItem("3hr - 4hr:", parkingSpot.priceWeekday2),
            CDBottomsheetAdapterItem("5hr - 12hr:", parkingSpot.priceWeekday3)))
        weekendsAdapter.setData(listOf(
            CDBottomsheetAdapterItem("1hr - 2hr:", parkingSpot.priceWeekend1),
            CDBottomsheetAdapterItem("3hr - 4hr:", parkingSpot.priceWeekend2),
            CDBottomsheetAdapterItem("5hr - 12hr:", parkingSpot.priceWeekend3)))
        with(binding){
            ownerLabel.text = parkingSpot.name
            parkingTypeLabel.text = when(parkingSpot.type) {
                "type1" -> "On Spot Parking"
                "type2" -> "Parking Lot"
                else -> "Custom"
            }
            priceLabel.text = "${parkingSpot.priceWeekday1} $"
            availabilityLabel.text = "${parkingSpot.availability} Car Spots"
            distanceLabel.text = "${parkingSpot.distance} km away"
            spotsAvailable.text = "${parkingSpot.availability} Spots"
            weekdaysRecyclerview.adapter = weekdaysAdapter
            weekendsRecyclerview.adapter = weekendsAdapter

        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.let {
                    val behavior = BottomSheetBehavior.from(it)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }

    override fun onSelectedListener(parkingSpot: CDBottomsheetAdapterItem) {

    }
}