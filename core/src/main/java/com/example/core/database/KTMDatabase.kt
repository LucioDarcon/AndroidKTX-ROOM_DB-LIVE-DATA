package com.example.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.dao.ProductDao
import com.example.core.dao.UserDao
import com.example.core.database.KTMDatabase.Companion.VERSION
import com.example.core.entities.ProductEntity
import com.example.core.entities.UserEntity

@Database(
    version      = VERSION,
    entities     = [UserEntity::class, ProductEntity::class],
    exportSchema = false
)
abstract class KTMDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

    companion object {

        const val VERSION = 1

        @Volatile
        private var INSTANCE: KTMDatabase? = null

        @JvmStatic
        fun getInstance(applicationContext: Context): KTMDatabase {
            synchronized(this) {
                return INSTANCE ?: Room
                    .databaseBuilder(
                        applicationContext,
                        KTMDatabase::class.java,
                        "KtmDb"
                    ).fallbackToDestructiveMigration().build()
                    .also { INSTANCE = it }
            }
        }


    }

}