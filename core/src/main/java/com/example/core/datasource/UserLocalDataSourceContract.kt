package com.example.core.datasource

import com.example.core.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSourceContract {

    suspend fun getUsers(): Flow<List<UserEntity>>

}

