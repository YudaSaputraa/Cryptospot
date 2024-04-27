package com.kom.cryptospot.data.repository

import com.kom.cryptospot.data.datasource.coin.CoinDataSource
import com.kom.cryptospot.data.mapper.toCoins
import com.kom.cryptospot.data.model.Coin
import com.kom.foodapp.utils.ResultWrapper
import com.kom.foodapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

class CoinRepositoryImpl(private val dataSource: CoinDataSource) : CoinRepository {
    override fun getCoins(vs_currency: String?): Flow<ResultWrapper<List<Coin>>> {
        return proceedFlow {
            dataSource.getCoinData(vs_currency).toCoins()
        }
    }
}