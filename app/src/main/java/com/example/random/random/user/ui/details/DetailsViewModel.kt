package com.example.random.random.user.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.random.random.user.network.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    lateinit var user: User
}
