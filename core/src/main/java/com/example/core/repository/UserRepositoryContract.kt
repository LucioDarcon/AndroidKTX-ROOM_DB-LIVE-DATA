package com.example.core.repository

import com.example.core.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepositoryContract {

    suspend fun getUsers(search: String?): Flow<List<UserEntity>?>

    suspend fun createUser(userEntity: UserEntity): Flow<Long?>

}

