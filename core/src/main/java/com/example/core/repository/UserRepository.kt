package com.example.core.repository

import com.example.core.dao.UserDao
import com.example.core.entities.UserEntity
import kotlinx.coroutines.flow.flow

class UserRepository(private val userDao: UserDao?) : UserRepositoryContract {

    override suspend fun getUsers(search: String?) = flow { this.emit(userDao?.getUsers(search)) }

    override suspend fun createUser(userEntity: UserEntity) = flow { this.emit(userDao?.insert(userEntity)) }


}