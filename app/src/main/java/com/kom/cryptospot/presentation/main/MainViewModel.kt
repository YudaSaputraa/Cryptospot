package com.kom.cryptospot.presentation.main

import androidx.lifecycle.ViewModel
import com.kom.cryptospot.data.repository.user.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun isUserLoggedIn() = userRepository.isLoggedIn()
}
