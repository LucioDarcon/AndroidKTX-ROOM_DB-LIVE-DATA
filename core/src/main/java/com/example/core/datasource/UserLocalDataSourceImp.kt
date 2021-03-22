package com.example.core.datasource

import com.example.core.dao.UserDao
import kotlinx.coroutines.flow.flow

class UserLocalDataSourceImp(private val userDao: UserDao) : UserLocalDataSourceContract {

    override suspend fun getUsers() = flow { this.emit(userDao.getUsers()) }

}