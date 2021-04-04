package com.example.androidktx.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.converter.UserConverter.converterUserToEntity
import com.example.androidktx.data.model.User
import com.example.core.repository.UserRepository
import com.example.core.entities.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository, private val search: String?) : ViewModel() {

    var usersLiveData : MutableLiveData<List<UserEntity>?> = MutableLiveData()
    var userCreated                                        = MutableLiveData<User>()

    init {
        getUsers()
    }

    private fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.getUsers(search).collect {
                usersLiveData.postValue(it)
            }
        }
    }

    fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.createUser(converterUserToEntity(user)).collect { userEntity ->
                userCreated.postValue(user)
            }
        }
    }

    class UserViewModelFactory(private val userRepository: UserRepository, private val search: String?) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository, search) as T
        }


    }


}