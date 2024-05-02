package com.kom.cryptospot.data.source.network.model.coindetail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoinItemByIdResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("web_slug")
    val webSlug: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("market_data")
    val marketData: MarketData?,
    @SerializedName("description")
    val description: Description?,
)
