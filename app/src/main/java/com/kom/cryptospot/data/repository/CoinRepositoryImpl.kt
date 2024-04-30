package com.kom.cryptospot.data.repository

import com.kom.cryptospot.data.datasource.coin.CoinDataSource
import com.kom.cryptospot.data.mapper.toCoins
import com.kom.cryptospot.data.model.Coin
import com.kom.foodapp.utils.ResultWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl(
    private val dataSource: CoinDataSource,
) : CoinRepository {
    override fun getCoins(vsCurrency: String): Flow<ResultWrapper<List<Coin>>> {
        return flow {
            emit(ResultWrapper.Loading())
            delay(1000)
            val coinsData = dataSource.getCoinData(vsCurrency).toCoins()
            emit(ResultWrapper.Success(coinsData))
        }
    }
}
