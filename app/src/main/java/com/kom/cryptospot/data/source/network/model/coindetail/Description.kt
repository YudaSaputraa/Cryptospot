package com.kom.cryptospot.data.source.network.model.coindetail

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Description(
    @SerializedName("en")
    val en: String?,
)
