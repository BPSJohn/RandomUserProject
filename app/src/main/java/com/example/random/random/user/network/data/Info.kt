package com.example.random.random.user.network.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info (

    @SerializedName("seed"    ) var seed    : String? = null,
    @SerializedName("results" ) var results : Int?    = null,
    @SerializedName("page"    ) var page    : Int?    = null,
    @SerializedName("version" ) var version : String? = null

) : Parcelable
