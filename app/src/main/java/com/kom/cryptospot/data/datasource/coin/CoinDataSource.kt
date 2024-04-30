package com.kom.cryptospot.data.datasource.coin

import com.kom.cryptospot.data.source.network.model.coin.CoinsResponse

interface CoinDataSource {
    suspend fun getCoinData(vs_currency: String? = "idr"): CoinsResponse
}
