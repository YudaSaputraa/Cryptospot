package com.kom.cryptospot.di

import com.kom.cryptospot.data.datasource.authentication.AuthDataSource
import com.kom.cryptospot.data.datasource.authentication.FirebaseAuthDataSource
import com.kom.cryptospot.data.datasource.coin.CoinApiDataSource
import com.kom.cryptospot.data.datasource.coin.CoinDataSource
import com.kom.cryptospot.data.datasource.coindetail.CoinDetailApiDataSource
import com.kom.cryptospot.data.datasource.coindetail.CoinDetailDataSource
import com.kom.cryptospot.data.repository.coin.CoinRepository
import com.kom.cryptospot.data.repository.coin.CoinRepositoryImpl
import com.kom.cryptospot.data.repository.coindetail.CoinDetailRepository
import com.kom.cryptospot.data.repository.coindetail.CoinDetailRepositoryImpl
import com.kom.cryptospot.data.repository.user.UserRepository
import com.kom.cryptospot.data.repository.user.UserRepositoryImpl
import com.kom.cryptospot.data.source.firebase.FirebaseService
import com.kom.cryptospot.data.source.firebase.FirebaseServiceImpl
import com.kom.cryptospot.data.source.network.services.CryptospotApiService
import com.kom.cryptospot.presentation.detail.DetailViewModel
import com.kom.cryptospot.presentation.home.HomeViewModel
import com.kom.cryptospot.presentation.login.LoginViewModel
import com.kom.cryptospot.presentation.profile.ProfileViewModel
import com.kom.cryptospot.presentation.profile.editprofile.EditProfileViewModel
import com.kom.cryptospot.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
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
            single<CoinDetailDataSource> { CoinDetailApiDataSource(get()) }
            single<AuthDataSource> { FirebaseAuthDataSource(get()) }
        }

    private val repository =
        module {
            single<CoinRepository> { CoinRepositoryImpl(get()) }
            single<CoinDetailRepository> { CoinDetailRepositoryImpl(get()) }
            single<UserRepository> { UserRepositoryImpl(get()) }
        }

    private val viewModelModule =
        module {
            viewModelOf(::HomeViewModel)
            viewModelOf(::LoginViewModel)
            viewModelOf(::ProfileViewModel)
            viewModelOf(::EditProfileViewModel)
            viewModelOf(::RegisterViewModel)
            viewModel { params ->
                // assisted injection
                DetailViewModel(
                    intent = params.get(),
                    repository = get(),
                )
            }
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
