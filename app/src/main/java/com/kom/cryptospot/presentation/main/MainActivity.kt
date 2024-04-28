package com.kom.cryptospot.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kom.cryptospot.data.source.network.services.CryptospotApiService
import com.kom.cryptospot.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        getDataFromApi()
    }

    private fun getDataFromApi() {
        val apiService = CryptospotApiService.invoke()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getCoins("idr", "ethereum")
                Log.d("Coins", "Response: $response")
            } catch (e: Exception) {
                Log.e("Coins Error", "Error: ${e.message}", e)
            }
        }
    }
}
