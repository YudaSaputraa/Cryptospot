package com.kom.cryptospot.data.repository.coindetail

import com.kom.cryptospot.data.datasource.coindetail.CoinDetailDataSource
import com.kom.cryptospot.data.mapper.toCoin
import com.kom.cryptospot.data.model.coin.CoinDetail
import com.kom.foodapp.utils.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
interface CoinDetailRepository {
    fun getCoinById(id: String): Flow<ResultWrapper<List<CoinDetail>>>
}

class CoinDetailRepositoryImpl(
    private val dataSource: CoinDetailDataSource,
) : CoinDetailRepository {
    override fun getCoinById(id: String): Flow<ResultWrapper<List<CoinDetail>>> {
        return flow {
            emit(ResultWrapper.Loading())
            delay(1000)
            val coinItemResponse = dataSource.getCoinById(id)
            val coinsDetailData = listOf(coinItemResponse.toCoin())
            emit(ResultWrapper.Success(coinsDetailData))
        }
    }
}
