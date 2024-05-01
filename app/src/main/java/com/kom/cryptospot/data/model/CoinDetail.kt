package com.kom.cryptospot.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
@Parcelize
data class CoinDetail(
    var id: String = UUID.randomUUID().toString(),
    var imgURL: String,
    var name: String,
    var price: Int,
    var coinSymbols: String,
    var coinDesc: String,
) : Parcelable
