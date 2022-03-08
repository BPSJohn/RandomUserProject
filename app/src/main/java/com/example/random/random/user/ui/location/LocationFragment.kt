package com.example.random.random.user.ui.location

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.random.random.user.R
import com.example.random.random.user.databinding.LocationFragmentBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import timber.log.Timber

class LocationFragment : Fragment() {

    private val locationFragmentArgs by navArgs<LocationFragmentArgs>()

    private val viewModel: LocationViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var userTextView: TextView

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            if (isGranted) {
                Timber.d("Permission granted by the user")
                enableMyLocation()
            } else {
                Timber.d("Permission denied by the user")
                view?.findNavController()?.navigate(
                    LocationFragmentDirections.actionLocationFragmentToDetailsFragment(
                        viewModel.user
                    )
                )
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.user = locationFragmentArgs.user

        val binding = LocationFragmentBinding.inflate(inflater)

        fusedLocationClient = LocationServices
            .getFusedLocationProviderClient(this.requireActivity())

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.locationRandomUser.text = viewModel.setRandomUserLocation()

        userTextView = binding.locationThisUser

        enableMyLocation()

        binding.locationButton.setOnClickListener { v: View ->
            binding.locationDistance.text = viewModel.getDistance()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            Timber.d("Getting permissions")
        }
        else{
            Timber.d("Permissions already granted.")
        }
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Timber.d("Case test")
            }

            fusedLocationClient.lastLocation.addOnSuccessListener(this.requireActivity()) { location ->

                if (location != null) {
                    viewModel.userLatitude = location.latitude
                    viewModel.userLongitude = location.longitude
                    val locationString = "${location.latitude}, ${location.longitude}"
                    userTextView.text = viewModel.setUserLocation()
                    Timber.d("Got the user's location: $locationString")

                }
            }
        }
        else {
            Timber.d("No permissions.")
        }
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this.requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

}
