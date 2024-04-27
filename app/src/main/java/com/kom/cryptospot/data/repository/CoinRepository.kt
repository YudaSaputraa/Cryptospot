package com.kom.cryptospot.data.repository

import com.kom.cryptospot.data.model.Coin
import com.kom.foodapp.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(vs_currency: String? = "idr"): Flow<ResultWrapper<List<Coin>>>
}