package com.kom.cryptospot.di

import com.kom.cryptospot.data.datasource.authentication.AuthDataSource
import com.kom.cryptospot.data.datasource.authentication.FirebaseAuthDataSource
import com.kom.cryptospot.data.datasource.coin.CoinApiDataSource
import com.kom.cryptospot.data.datasource.coin.CoinDataSource
import com.kom.cryptospot.data.repository.CoinRepository
import com.kom.cryptospot.data.repository.CoinRepositoryImpl
import com.kom.cryptospot.data.repository.UserRepository
import com.kom.cryptospot.data.repository.UserRepositoryImpl
import com.kom.cryptospot.data.source.firebase.FirebaseService
import com.kom.cryptospot.data.source.firebase.FirebaseServiceImpl
import com.kom.cryptospot.data.source.network.services.CryptospotApiService
import com.kom.cryptospot.presentation.home.HomeViewModel
import com.kom.cryptospot.presentation.login.LoginViewModel
import com.kom.cryptospot.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.scope.get
import org.koin.dsl.module

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
object AppModules {
    private val networkModule =
        module {
            single<CryptospotApiService> { CryptospotApiService.invoke() }
        }

    private val firebaseModule =
        module {
            single<FirebaseService> { FirebaseServiceImpl() }
        }

    private val datasource =
        module {
            single<CoinDataSource> { CoinApiDataSource(get()) }
            single<AuthDataSource> { FirebaseAuthDataSource(get()) }
        }

    private val repository =
        module {
            single<CoinRepository> { CoinRepositoryImpl(get()) }
            single<UserRepository> { UserRepositoryImpl(get()) }
        }

    private val viewModelModule =
        module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::LoginViewModel)
            viewModelOf(::ProfileViewModel)
        }

    val modules =
        listOf(
            networkModule,
            firebaseModule,
            datasource,
            repository,
            viewModelModule,
        )
}
