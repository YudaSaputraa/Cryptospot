// package com.kom.cryptospot.data.datasource.favourite
//
// import com.kom.cryptospot.data.source.local.database.dao.FavouriteDao
// import com.kom.cryptospot.data.source.local.database.entity.FavouriteEntity
// import kotlinx.coroutines.flow.Flow
//
// class FavouriteDataSourceImpl(private val dao: FavouriteDao) : FavouriteDataSource {
//    override fun getAllCoins(): Flow<List<FavouriteEntity>> = dao.getAllCoins()
//
//    override suspend fun insertFavourite(favourite: FavouriteEntity): Long = dao.insertFavourite(favourite)
//
//    override suspend fun updateFavourite(favourite: FavouriteEntity): Int = dao.updateFavourite(favourite)
//
//    override suspend fun deleteFavourite(favourite: FavouriteEntity): Int = dao.deleteFavourite(favourite)
//
//    override suspend fun deleteAll() = dao.deleteAll()
// }
