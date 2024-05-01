package com.kom.cryptospot.data.repository

import com.kom.cryptospot.data.datasource.authentication.AuthDataSource
import com.kom.foodapp.utils.ResultWrapper
import com.kom.foodapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    @Throws(exceptionClasses = [Exception::class])
    fun doLogin(
        email: String,
        password: String,
    ): Flow<ResultWrapper<Boolean>>

    fun reqChangePasswordByEmailWithoutLogin(email: String): Flow<ResultWrapper<Boolean>>

    fun isLoggedIn(): Boolean

    fun doLogout(): Boolean
}

class UserRepositoryImpl(private val dataSource: AuthDataSource) : UserRepository {
    override fun doLogin(
        email: String,
        password: String,
    ): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.doLogin(email, password) }
    }

    override fun reqChangePasswordByEmailWithoutLogin(email: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.reqChangePasswordByEmailWithoutLogin(email) }
    }

    override fun isLoggedIn(): Boolean {
        return dataSource.isLoggedIn()
    }

    override fun doLogout(): Boolean {
        return dataSource.doLogout()
    }
}
