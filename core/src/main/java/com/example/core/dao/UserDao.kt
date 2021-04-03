package com.example.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity?): Long
    
    @Query("SELECT * FROM users WHERE name LIKE '%' || :userName || '%'")
    suspend fun getUsers(userName: String?): List<UserEntity>

}