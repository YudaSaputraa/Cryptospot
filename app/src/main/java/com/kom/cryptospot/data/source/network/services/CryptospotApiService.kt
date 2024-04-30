package com.kom.cryptospot.data.source.network.services

import com.kom.cryptospot.BuildConfig
import com.kom.cryptospot.data.source.network.model.coin.CoinsResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
interface CryptospotApiService {
    @GET("api/v3/coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String? = null,
        @Query("ids") ids: String? = null,
    ): CoinsResponse

    companion object {
        @JvmStatic
        operator fun invoke(): CryptospotApiService {
            val okHttpClient =
                OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original: Request = chain.request()
                        val requestBuilder: Request.Builder =
                            original.newBuilder()
                                .addHeader("accept", "application/json")
                                .addHeader("x-cg-demo-api-key", BuildConfig.API_TOKEN)
                        val request: Request = requestBuilder.build()
                        chain.proceed(request)
                    }
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(CryptospotApiService::class.java)
        }
    }
}
