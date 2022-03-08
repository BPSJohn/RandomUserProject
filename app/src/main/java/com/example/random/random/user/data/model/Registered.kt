package com.example.random.random.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Registered (

    @SerializedName("date" ) var date : String? = null,
    @SerializedName("age"  ) var age  : Int?    = null

) : Parcelable
