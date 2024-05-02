package com.kom.cryptospot.data.datasource.authentication

import com.kom.cryptospot.data.model.user.User
import com.kom.cryptospot.data.model.user.toUser
import com.kom.cryptospot.data.source.firebase.FirebaseService
import java.lang.Exception

interface AuthDataSource {
    @Throws(exceptionClasses = [Exception::class])
    suspend fun doLogin(
        email: String,
        password: String,
    ): Boolean

    @Throws(exceptionClasses = [java.lang.Exception::class])
    suspend fun doRegister(
        fullName: String,
        email: String,
        password: String,
    ): Boolean

    suspend fun updateProfile(fullName: String? = null): Boolean

    suspend fun updatePassword(newPassword: String): Boolean

    suspend fun updateEmail(newEmail: String): Boolean

    fun getCurrentUser(): User?

    suspend fun reqChangePasswordByEmailWithoutLogin(email: String): Boolean

    fun isLoggedIn(): Boolean

    fun doLogout(): Boolean
}

class FirebaseAuthDataSource(private val service: FirebaseService) : AuthDataSource {
    override suspend fun doLogin(
        email: String,
        password: String,
    ): Boolean {
        return service.doLogin(email, password)
    }

    override suspend fun doRegister(
        fullName: String,
        email: String,
        password: String,
    ): Boolean {
        return service.doRegister(
            fullName = fullName,
            email = email,
            password = password,
        )
    }

    override suspend fun updateProfile(fullName: String?): Boolean {
        return service.updateProfile(fullName)
    }

    override suspend fun updatePassword(newPassword: String): Boolean {
        return service.updatePassword(newPassword)
    }

    override suspend fun updateEmail(newEmail: String): Boolean {
        return service.updateEmail(newEmail)
    }

    override suspend fun reqChangePasswordByEmailWithoutLogin(email: String): Boolean {
        return service.reqChangePasswordByEmailWithoutLogin(email)
    }

    override fun isLoggedIn(): Boolean {
        return service.isLoggedIn()
    }

    override fun doLogout(): Boolean {
        return service.doLogout()
    }

    override fun getCurrentUser(): User? {
        return service.getCurrentUser().toUser()
    }
}
