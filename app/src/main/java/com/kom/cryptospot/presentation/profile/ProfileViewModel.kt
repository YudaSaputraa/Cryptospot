package com.kom.cryptospot.presentation.profile

import androidx.lifecycle.ViewModel
import com.kom.cryptospot.data.repository.user.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun isUserLoggedOut() = userRepository.doLogout()
}
