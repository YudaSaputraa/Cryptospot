package com.kom.cryptospot.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kom.cryptospot.R
import com.kom.cryptospot.data.datasource.authentication.AuthDataSource
import com.kom.cryptospot.data.datasource.authentication.FirebaseAuthDataSource
import com.kom.cryptospot.data.repository.user.UserRepository
import com.kom.cryptospot.data.repository.user.UserRepositoryImpl
import com.kom.cryptospot.data.source.firebase.FirebaseService
import com.kom.cryptospot.data.source.firebase.FirebaseServiceImpl
import com.kom.cryptospot.data.source.network.services.CryptospotApiService
import com.kom.cryptospot.databinding.ActivityMainBinding
import com.kom.cryptospot.presentation.login.LoginActivity
import com.kom.foodapp.utils.GenericViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val firebaseService: FirebaseService = FirebaseServiceImpl()
        val authDataSource: AuthDataSource = FirebaseAuthDataSource(firebaseService)
        val userRepository: UserRepository = UserRepositoryImpl(authDataSource)
        GenericViewModelFactory.create(MainViewModel(userRepository))
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
            when (destination.id) {
                R.id.menu_tab_profile -> {
                    if (!viewModel.isUserLoggedIn()) {
                        navigateToLogin()
                        controller.popBackStack(R.id.menu_tab_home, false)
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun getDataFromApi() {
        val apiService = CryptospotApiService.invoke()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getCoinById("bitcoin")
                Log.d("Coins", "Response: $response")
            } catch (e: Exception) {
                Log.e("Coins Error", "Error: ${e.message}", e)
            }
        }
    }
}
