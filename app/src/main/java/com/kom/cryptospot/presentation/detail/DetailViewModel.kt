package com.kom.cryptospot.presentation.detail

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.cryptospot.data.repository.coindetail.CoinDetailRepository
import kotlinx.coroutines.Dispatchers

class DetailViewModel(
    private val repository: CoinDetailRepository,
    intent: Intent,
) : ViewModel() {
    val coinId = intent.getStringExtra(DetailActivity.EXTRA_COIN)
    val link: String = "https://www.coingecko.com/en/coins/$coinId"

    fun coinData(id: String?) = repository.getCoinById(id).asLiveData(Dispatchers.IO)
}
