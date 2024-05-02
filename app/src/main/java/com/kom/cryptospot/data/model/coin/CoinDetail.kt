package com.kom.cryptospot.data.model.coin

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
    val coinId: String,
    val name: String,
    val symbol: String,
    val imgURL: String,
    val price: Int,
    val coinDesc: String,
) : Parcelable
