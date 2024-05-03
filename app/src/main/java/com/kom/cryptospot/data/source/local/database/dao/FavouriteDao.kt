// package com.kom.cryptospot.data.source.local.database.dao
//
// import androidx.room.Dao
// import androidx.room.Delete
// import androidx.room.Insert
// import androidx.room.OnConflictStrategy
// import androidx.room.Query
// import androidx.room.Update
// import com.kom.cryptospot.data.source.local.database.entity.FavouriteEntity
// import kotlinx.coroutines.flow.Flow
//
// @Dao
// interface FavouriteDao {
//    @Query("SELECT * FROM FAVOURITES")
//    fun getAllCoins(): Flow<List<FavouriteEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavourite(favourite: FavouriteEntity): Long
//
//    @Update
//    suspend fun updateFavourite(favourite: FavouriteEntity): Int
//
//    @Delete
//    suspend fun deleteFavourite(favourite: FavouriteEntity): Int
//
//    @Query("DELETE FROM FAVOURITES")
//    suspend fun deleteAll()
// }
