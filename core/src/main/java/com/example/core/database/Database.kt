package com.example.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.database.Database.Companion.VERSION
import com.example.core.entities.UserEntity

@androidx.room.Database(
    version      = VERSION,
    entities     = [UserEntity::class],
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    companion object {

        const val VERSION = 1

        @Volatile
        private var INSTANCE: Database? = null

        @JvmStatic
        fun getInstance(applicationContext: Context): Database {
            synchronized(this) {
                return INSTANCE ?: Room
                    .databaseBuilder(
                        applicationContext,
                        Database::class.java,
                        "androidktp"
                    ).fallbackToDestructiveMigration().build()
                    .also { INSTANCE = it }
            }
        }


    }

}