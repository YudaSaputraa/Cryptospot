package com.kom.cryptospot.data.datasource.authentication

import com.kom.cryptospot.data.source.firebase.FirebaseService

interface AuthDataSource {
    @Throws(exceptionClasses = [Exception::class])
    suspend fun doLogin(
        email: String,
        password: String,
    ): Boolean

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

    override suspend fun reqChangePasswordByEmailWithoutLogin(email: String): Boolean {
        return service.reqChangePasswordByEmailWithoutLogin(email)
    }

    override fun isLoggedIn(): Boolean {
        return service.isLoggedIn()
    }

    override fun doLogout(): Boolean {
        return service.doLogout()
    }
}
