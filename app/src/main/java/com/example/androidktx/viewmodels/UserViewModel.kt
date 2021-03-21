package com.example.androidktx.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.data.model.User
import com.example.androidktx.data.repository.UserRepositoryMock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class UserViewModel(private val userRepositoryMock: UserRepositoryMock) : ViewModel() {

    var usersLiveData = MutableLiveData<List<User>>()

    suspend fun getMockUsers() {
        userRepositoryMock.getListUsers().collect { listUsers ->
            usersLiveData.value = listUsers
        }
    }

    class UserViewModelFactory(private val userRepositoryMock: UserRepositoryMock) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(userRepositoryMock) as T
        }

    }

}