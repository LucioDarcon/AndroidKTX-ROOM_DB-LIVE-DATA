package com.example.core.datasource

import com.example.core.dao.UserDao
import com.example.core.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLocalRepository(private val userDao: UserDao?) : UserLocalRepositoryContract {

    override suspend fun getUsers() = flow { this.emit(userDao?.getUsers()) }

    override suspend fun createUser(userEntity: UserEntity) = flow { this.emit(userDao?.insert(userEntity)) }


}