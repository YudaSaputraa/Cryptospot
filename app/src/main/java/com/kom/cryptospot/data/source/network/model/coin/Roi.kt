package com.kom.cryptospot.data.source.network.model.coin

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Roi(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("percentage")
    val percentage: Double?,
    @SerializedName("times")
    val times: Double?,
)
