package com.example.random.random.user.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RandomUserResult (

    @SerializedName("results" ) var results : List<User>,
    @SerializedName("info"    ) var info    : Info

) : Parcelable
