package com.example.core.datasource

import com.example.core.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalRepositoryContract {

    suspend fun getUsers(): Flow<List<UserEntity>?>

    suspend fun createUser(userEntity: UserEntity): Flow<Long?>

}

