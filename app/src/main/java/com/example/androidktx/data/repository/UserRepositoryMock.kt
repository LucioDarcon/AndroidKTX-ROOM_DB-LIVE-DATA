package com.example.androidktx.data.repository

import com.example.androidktx.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserRepositoryMock {

    private val listUser = listOf<User>(
        User("Davi", "Davi@gmai.com"),
        User("Lucas", "Lucas@gmai.com"),
        User("Matos", "Matos@gmai.com"),
        User("João", "João@gmai.com")
    )

    suspend fun getListUsers(): Flow<List<User>> = withContext(Dispatchers.IO) {
        flow { this.emit(listUser) }
    }

}