package com.example.random.random.user.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Dob (

  @SerializedName("date" ) var date : String? = null,
  @SerializedName("age"  ) var age  : String?    = null

): Parcelable
