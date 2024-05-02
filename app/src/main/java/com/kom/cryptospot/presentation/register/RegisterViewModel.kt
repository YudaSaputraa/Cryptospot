package com.kom.cryptospot.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.cryptospot.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    fun doRegister(
        email: String,
        password: String,
        username: String,
    ) = repository
        .doRegister(
            fullName = username,
            email = email,
            password = password,
        )
        .asLiveData(Dispatchers.IO)
}
