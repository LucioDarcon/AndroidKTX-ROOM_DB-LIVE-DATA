package com.example.androidktx.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.converter.UserConverter.converterUserToEntity
import com.example.androidktx.data.model.User
import com.example.core.datasource.UserLocalRepository
import com.example.core.entities.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(private val userLocalRepository: UserLocalRepository, private val lifecycleCoroutineScope: LifecycleCoroutineScope) : ViewModel() {

    var usersLiveData : MutableLiveData<List<UserEntity>?> = MutableLiveData()
    var userCreated                                        = MutableLiveData<User>()

    init {
        getUsers()
    }

    private fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
             userLocalRepository.getUsers().collect {
                 usersLiveData.postValue(it)
            }
        }
    }

    fun createUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userLocalRepository.createUser(converterUserToEntity(user)).collect { userEntity ->
                userCreated.value = user
            }
        }
    }

    class UserViewModelFactory(private val userLocalRepository: UserLocalRepository, private val lifecycleCoroutineScope: LifecycleCoroutineScope) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(userLocalRepository, lifecycleCoroutineScope) as T
        }


    }


}