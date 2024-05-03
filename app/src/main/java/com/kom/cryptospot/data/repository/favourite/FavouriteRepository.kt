// package com.kom.cryptospot.data.repository.favourite
//
// import com.kom.cryptospot.data.model.Favourite
// import com.kom.cryptospot.data.model.coin.Coin
// import com.kom.cryptospot.data.source.local.database.entity.FavouriteEntity
// import com.kom.foodapp.utils.ResultWrapper
// import kotlinx.coroutines.flow.Flow
//
// interface FavouriteRepository {
//    fun getFavouriteData(): Flow<ResultWrapper<List<FavouriteEntity>>>
//
//    fun createFavourite(coin: Coin): Flow<ResultWrapper<Boolean>>
//
//    fun deleteFavourite(item: Favourite): Flow<ResultWrapper<Boolean>>
//
//    fun deleteAll()
// }
