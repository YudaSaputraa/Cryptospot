package com.kom.cryptospot.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.cryptospot.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    fun doRegister(
        email: String,
        username: String,
        password: String,
    ) = repository
        .doRegister(
            fullName = username,
            email = email,
            password = password,
        )
        .asLiveData(Dispatchers.IO)
}
