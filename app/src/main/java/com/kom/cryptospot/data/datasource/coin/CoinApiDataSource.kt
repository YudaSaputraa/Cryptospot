package com.kom.cryptospot.data.datasource.coin

import com.kom.cryptospot.data.source.network.model.coin.CoinsResponse
import com.kom.cryptospot.data.source.network.services.CryptospotApiService

class CoinApiDataSource(
    private val service: CryptospotApiService,
) : CoinDataSource {
    override suspend fun getCoinData(vsCurrency: String): CoinsResponse {
        return service.getCoins(vsCurrency)
    }
}
