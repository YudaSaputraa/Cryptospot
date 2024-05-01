package com.kom.cryptospot.data.repository

import com.kom.cryptospot.data.datasource.authentication.AuthDataSource
import com.kom.cryptospot.data.model.User
import com.kom.foodapp.utils.ResultWrapper
import com.kom.foodapp.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    @Throws(exceptionClasses = [Exception::class])
    fun doLogin(
        email: String,
        password: String,
    ): Flow<ResultWrapper<Boolean>>

    @Throws(exceptionClasses = [java.lang.Exception::class])
    fun doRegister(
        fullName: String,
        email: String,
        password: String,
    ): Flow<ResultWrapper<Boolean>>

    fun updateProfile(fullName: String? = null): Flow<ResultWrapper<Boolean>>

    fun updatePassword(newPassword: String): Flow<ResultWrapper<Boolean>>

    fun updateEmail(newEmail: String): Flow<ResultWrapper<Boolean>>

    fun getCurrentUser(): User?

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

    override fun doRegister(
        fullName: String,
        email: String,
        password: String,
    ): Flow<ResultWrapper<Boolean>> {
        return proceedFlow {
            dataSource.doRegister(
                fullName = fullName,
                email = email,
                password = password,
            )
        }
    }

    override fun updateProfile(fullName: String?): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.updateProfile(fullName = fullName) }
    }

    override fun updatePassword(newPassword: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.updatePassword(newPassword) }
    }

    override fun updateEmail(newEmail: String): Flow<ResultWrapper<Boolean>> {
        return proceedFlow { dataSource.updateEmail(newEmail) }
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

    override fun getCurrentUser(): User? {
        return dataSource.getCurrentUser()
    }
}
