package com.kom.cryptospot.data.datasource.coindetail

import com.kom.cryptospot.data.source.network.model.coindetail.CoinItemByIdResponse

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
interface CoinDetailDataSource {
    suspend fun getCoinById(id: String): CoinItemByIdResponse
}
