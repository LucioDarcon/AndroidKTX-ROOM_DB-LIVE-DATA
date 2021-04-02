package com.example.core.provider

import android.content.Context
import com.example.core.dao.UserDao
import com.example.core.database.KTMDatabase

object Providers {

    fun provideDataBase(applicationContext: Context?): KTMDatabase? {
        return applicationContext?.let { KTMDatabase.getInstance(applicationContext) }
    }

    fun provideUserDao(applicationContext: Context?): UserDao? {
        return applicationContext?.let { KTMDatabase.getInstance(it).userDao() }
    }



}