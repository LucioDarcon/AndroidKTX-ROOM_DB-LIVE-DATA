package com.example.core.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity        = UserEntity::class,
            parentColumns = ["id"],
            childColumns  = ["user_id"]
        )
    ])
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?            = null,
    @ColumnInfo(name = "user_id")
    val userId: Long?        = null,
    val name: String?        = null,
    val description: String? = null,
    val price: Float?        = null
)