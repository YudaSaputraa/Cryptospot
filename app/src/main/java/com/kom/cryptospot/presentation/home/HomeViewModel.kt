package com.kom.cryptospot.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.cryptospot.data.repository.CoinRepository
import kotlinx.coroutines.Dispatchers

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class HomeViewModel(
    private val coinRepository: CoinRepository,
) : ViewModel() {
    fun getCoins(vsCurrency: String) = coinRepository.getCoins(vsCurrency).asLiveData(Dispatchers.IO)
}
