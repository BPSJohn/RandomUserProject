package com.example.random.random.user.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timezone (

  @SerializedName("offset"      ) var offset      : String? = null,
  @SerializedName("description" ) var description : String? = null

): Parcelable
