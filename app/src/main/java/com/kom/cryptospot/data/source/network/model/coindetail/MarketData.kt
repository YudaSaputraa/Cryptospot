package com.kom.cryptospot.data.source.network.model.coindetail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice?,
)
