package com.example.random.random.user.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id (

  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("value" ) var value : String? = null

): Parcelable
