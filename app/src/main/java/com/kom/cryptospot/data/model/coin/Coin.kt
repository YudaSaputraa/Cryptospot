package com.kom.cryptospot.data.model.coin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Coin(
    var id: String = UUID.randomUUID().toString(),
    var coinId: String,
    var imgURL: String,
    var name: String,
    var price: Double,
    var coinSymbols: String,
) : Parcelable
