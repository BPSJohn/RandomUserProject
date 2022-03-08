package com.example.random.random.user.ui.location

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.random.random.user.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    lateinit var user: User

    private val distanceHelper = DistanceHelper()

    var userLatitude: Double = 0.0

    var userLongitude : Double = 0.0

    fun getDistance() : String {
        var randomLongitude : Double = 0.0
        var randomLatitude : Double = 0.0

        user.location?.coordinates?.longitude?.let {
            randomLongitude = it.toDouble()
        }

        user.location?.coordinates?.latitude?.let {
            randomLatitude = it.toDouble()
        }
        val distance = distanceHelper.distance(randomLatitude, randomLongitude, userLatitude, userLongitude, 'K').toString()
        return "$distance kilometers"
    }

    fun setUserLocation() : String {
        return "Location of this phone: ($userLongitude, $userLatitude)"
    }

    fun setRandomUserLocation() : String {
        val latitude = user.location?.coordinates?.latitude
        val longitude = user.location?.coordinates?.longitude
        if(latitude != null && longitude != null)
        {
            return "Location of random user: ($longitude, $latitude)"
        }
        else
        {
            return "No location."
        }
    }
}
