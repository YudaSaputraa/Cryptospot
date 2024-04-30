package com.kom.cryptospot.data.datasource.coin

import com.kom.cryptospot.data.source.network.model.coin.CoinsResponse
import com.kom.cryptospot.data.source.network.services.CryptospotApiService

class CoinApiDataSource(private val service: CryptospotApiService) : CoinDataSource {
    override suspend fun getCoinData(vs_currency: String?): CoinsResponse {
        return service.getCoins(vs_currency)
    }
}