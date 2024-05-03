package com.kom.cryptospot.data.source.network.model.coindetail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CurrentPrice(
    @SerializedName("idr")
    val idr: Double?,
    @SerializedName("usd")
    val usd: Double?,
)
