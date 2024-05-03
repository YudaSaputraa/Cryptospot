package com.kom.cryptospot.data.mapper

import com.kom.cryptospot.data.model.coin.CoinDetail
import com.kom.cryptospot.data.source.network.model.coindetail.CoinItemByIdResponse

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
fun CoinItemByIdResponse?.toCoin(): CoinDetail {
    return CoinDetail(
        coinId = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
        symbol = this?.symbol.orEmpty(),
        imgURL = this?.image?.large.orEmpty(),
        price = this?.marketData?.currentPrice?.usd ?: 0.0,
        coinDesc = this?.description?.en.orEmpty(),
    )
}

fun Collection<CoinItemByIdResponse>?.toCoins() =
    this?.map {
        it.toCoin()
    } ?: listOf()
