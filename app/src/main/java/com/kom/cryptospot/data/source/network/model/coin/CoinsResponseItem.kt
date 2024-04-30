package com.kom.cryptospot.data.source.network.model.coin

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoinsResponseItem(
    @SerializedName("current_price")
    val currentPrice: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("market_cap")
    val marketCap: Long?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?,
)
