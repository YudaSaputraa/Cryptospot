package com.kom.cryptospot.data.mapper

import com.kom.cryptospot.data.model.coin.Coin
import com.kom.cryptospot.data.source.network.model.coin.CoinsResponseItem

fun CoinsResponseItem?.toCoin() =
    Coin(
        imgURL = this?.image.orEmpty(),
        name = this?.name.orEmpty(),
        price = this?.currentPrice ?: 0.0,
        coinSymbols = this?.symbol ?: "null",
        coinId = this?.id.toString(),
    )

fun Collection<CoinsResponseItem>?.toCoins() =
    this?.map {
        it.toCoin()
    } ?: listOf()
