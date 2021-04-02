package com.example.androidktx.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.converter.UserConverter
import com.example.androidktx.converter.UserConverter.converterUserEntityToDomain
import com.example.androidktx.converter.UserConverter.converterUserToEntity
import com.example.androidktx.data.model.User
import com.example.core.datasource.UserLocalRepository
import kotlinx.coroutines.flow.collect

class UserViewModel(private val userLocalRepository: UserLocalRepository) : ViewModel() {

    var usersLiveData = MutableLiveData<List<User>>()
    var userCreated   = MutableLiveData<User>()

    suspend fun getUsers() {
        userLocalRepository.getUsers().collect { listUsers ->
            val users: ArrayList<User> = arrayListOf()
            listUsers?.forEach { userEntity ->
                users.add(UserConverter.converterUserEntityToDomain(userEntity))
            }
            usersLiveData.value = users
        }
    }

    suspend fun createUser(user: User) {
        userLocalRepository.createUser(converterUserToEntity(user)).collect { userEntity ->
            userCreated.value = user
        }
    }

    class UserViewModelFactory(private val userLocalRepository: UserLocalRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(userLocalRepository) as T
        }

    }

}