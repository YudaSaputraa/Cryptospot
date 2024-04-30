package com.kom.cryptospot.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kom.cryptospot.R
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
        setBottomNavbar()
    }

    private fun setBottomNavbar() {
        val navController = findNavController(R.id.nav_host)
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, argumen ->
        }
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
