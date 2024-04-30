package com.kom.cryptospot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Coin(
    var id: String = UUID.randomUUID().toString(),
    var imgURL: String,
    var name: String,
    var price: Double,
    var coinSymbols: String,
) : Parcelable
