package com.kom.cryptospot.data.mapper

import com.kom.cryptospot.data.model.CoinDetail
import com.kom.cryptospot.data.source.network.model.coindetail.CoinItemByIdResponse

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
fun CoinItemByIdResponse?.toCoin() =
    CoinDetail(
        imgURL = this?.image?.large.orEmpty(),
        name = this?.name.orEmpty(),
        price = this?.marketData?.currentPrice?.usd ?: 0,
        coinSymbols = this?.symbol.orEmpty(),
        coinDesc = this?.description?.en.orEmpty(),
    )

fun Collection<CoinItemByIdResponse>?.toCoins() =
    this?.map {
        it.toCoin()
    } ?: listOf()
