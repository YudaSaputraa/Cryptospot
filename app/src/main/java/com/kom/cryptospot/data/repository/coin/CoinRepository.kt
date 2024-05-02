package com.kom.cryptospot.data.repository.coin

import com.kom.cryptospot.data.model.coin.Coin
import com.kom.foodapp.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(vsCurrency: String): Flow<ResultWrapper<List<Coin>>>
}
