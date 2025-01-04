package com.example.app3.ui.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.app3.R
import com.example.app3.databinding.FragmentHomeMapBinding
import com.example.app3.utils.ModalBottomSheetDialog
import com.example.app3.utils.handleError
import com.example.app3.utils.vectorToBitmap
import com.example.domain.models.CDParkingSpot
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home_map), ParkingAdapter.OnParkingClickListener,
    OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationChangeListener,
    GoogleMap.OnMarkerClickListener {

    lateinit var binding: FragmentHomeMapBinding
    private val viewModel by navGraphViewModels<HomeViewModel>(R.id.mobile_navigation) { defaultViewModelProviderFactory }
    private lateinit var map: GoogleMap

    private lateinit var parkingAdapter: ParkingAdapter
    private var dialog: AlertDialog? = null
    private var followLocation: Boolean = false

    private var focusedMarker: CDParkingSpot? = null
    private var focusedCircle: Circle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeMapBinding.bind(view)
        val supportMapFragment =
            childFragmentManager.findFragmentByTag("mapFragment") as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        setupUI()
        setupObservables()
    }

    private fun setupUI() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.fragmentContainerView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        parkingAdapter = ParkingAdapter(listener = this)
        binding.categories.adapter = parkingAdapter
        binding.locationButton.setOnClickListener {
            followLocation = !followLocation
            Toast.makeText(this.context, "Tracking is turned "+if (followLocation) "on" else "off", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun setupObservables() {

        viewModel.spotsList.observe(viewLifecycleOwner) { list ->
            list.forEach {
                if (it.lat != null && it.lng != null) {
                    val marker = MarkerOptions()
                        .position(LatLng(it.lat!!, it.lng!!))
                        .icon(vectorToBitmap(R.drawable.ic_map_pointer, getColor(requireContext(), R.color.primary),
                            this.requireContext().resources))
                    map.addMarker(marker)?.tag = it
                }
            }
            parkingAdapter.setData(list)
        }

        viewModel.error.observe(viewLifecycleOwner, requireActivity().handleError())

    }

    fun zoomToItem(location: LatLng){
        map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude,location.longitude)))
        map.animateCamera(CameraUpdateFactory.zoomTo(15F))
    }

    override fun onOpenListener(parkingSpot: CDParkingSpot) {
        focusedCircle?.remove()
        val location = LatLng(parkingSpot.lat!!, parkingSpot.lng!!)
        zoomToItem(location)
        val circleOptions = CircleOptions()
            .center(location)
            .radius(100.0)
            .strokeColor(getColor(requireContext(),R.color.primary))
            .fillColor(ColorUtils.setAlphaComponent(getColor(requireContext(), R.color.white) ,180))
        focusedCircle = map.addCircle(circleOptions)
        val bottomSheetDialog = ModalBottomSheetDialog(parkingSpot)
        requireActivity().supportFragmentManager.let {
            bottomSheetDialog.show(it, ModalBottomSheetDialog.TAG)
        }
    }

    override fun onCloseListener() {

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        viewModel.getNearParkingSpots()
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnMyLocationChangeListener(this)
        googleMap.isMyLocationEnabled = true
        googleMap.isBuildingsEnabled = false
        googleMap.uiSettings.isMyLocationButtonEnabled = false

    }

    override fun onMyLocationButtonClick(): Boolean {
        followLocation = !followLocation
        Toast.makeText(this.context, "Tracking is turned "+if (followLocation) "on" else "off", Toast.LENGTH_SHORT)
            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationChange(location: Location) {
        if (followLocation) {
            zoomToItem(LatLng(location.latitude, location.longitude))

            Toast.makeText(this.context, "Current location:\n$location", Toast.LENGTH_LONG)
                .show()
        }

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        focusedCircle?.remove()
        focusedMarker = marker.tag as? CDParkingSpot
        zoomToItem(marker.position)
        val circleOptions = CircleOptions()
            .center(marker.position)
            .radius(100.0)
            .strokeColor(getColor(requireContext(),R.color.primary))
            .fillColor(ColorUtils.setAlphaComponent(getColor(requireContext(), R.color.white) ,180))
        focusedCircle = map.addCircle(circleOptions)
        return false
    }

}
