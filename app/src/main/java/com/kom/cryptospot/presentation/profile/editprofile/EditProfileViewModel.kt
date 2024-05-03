package com.kom.cryptospot.presentation.profile.editprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.cryptospot.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers

class EditProfileViewModel(private val repository: UserRepository) : ViewModel() {
    fun getCurrentUser() =
        repository
            .getCurrentUser()

    fun updateProfileName(fullName: String) =
        repository
            .updateProfile(fullName = fullName)
            .asLiveData(Dispatchers.IO)

    fun updateProfileEmail(email: String) =
        repository
            .updateEmail(newEmail = email)
            .asLiveData(Dispatchers.IO)

    fun createRequestChangePass() {
        repository.reqChangePasswordByEmail()
    }
}
