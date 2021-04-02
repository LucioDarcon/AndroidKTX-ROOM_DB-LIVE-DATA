package com.example.androidktx.converter

import com.example.androidktx.data.model.User
import com.example.core.entities.UserEntity

object UserConverter {

    fun converterUserEntityToDomain(userEntity: UserEntity): User {
        return User(id = userEntity.id, name = userEntity.name, email = userEntity.email)
    }

    fun converterUserToEntity(user: User): UserEntity {
        return UserEntity(id = user.id, name = user.name, email = user.email)
    }

}