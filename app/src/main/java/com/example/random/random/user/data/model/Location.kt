package com.example.random.random.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (

  @SerializedName("street"      ) var street      : Street?      = Street(),
  @SerializedName("city"        ) var city        : String?      = null,
  @SerializedName("state"       ) var state       : String?      = null,
  @SerializedName("country"     ) var country     : String?      = null,
  @SerializedName("postcode"    ) var postcode    : String?         = null,
  @SerializedName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
  @SerializedName("timezone"    ) var timezone    : Timezone?    = Timezone()

): Parcelable
