package com.kom.cryptospot.data.datasource.coindetail

import com.kom.cryptospot.data.source.network.model.coindetail.CoinDetailResponse
import com.kom.cryptospot.data.source.network.services.CryptospotApiService

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class CoinDetailApiDataSource(
    private val service: CryptospotApiService,
) : CoinDetailDataSource {
    override suspend fun getCoinById(id: String): CoinDetailResponse {
        return service.getCoinById(id)
    }
}
