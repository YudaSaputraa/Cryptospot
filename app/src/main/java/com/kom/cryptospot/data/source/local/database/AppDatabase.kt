// package com.kom.cryptospot.data.source.local.database
//
// import android.content.Context
// import androidx.room.Database
// import androidx.room.Room
// import androidx.room.RoomDatabase
// import com.kom.cryptospot.data.source.local.database.dao.FavouriteDao
// import com.kom.cryptospot.data.source.local.database.entity.FavouriteEntity
//
// @Database(
//    entities = [FavouriteEntity::class],
//    version = 1,
//    exportSchema = true,
// )
// abstract class AppDatabase : RoomDatabase() {
//    abstract fun favouriteDao(): FavouriteDao
//
//    companion object {
//        private const val DB_NAME = "Cryptoapp.db"
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun createInstance(context: Context): AppDatabase {
//            return Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                DB_NAME,
//            ).fallbackToDestructiveMigration().build()
//        }
//    }
// }
