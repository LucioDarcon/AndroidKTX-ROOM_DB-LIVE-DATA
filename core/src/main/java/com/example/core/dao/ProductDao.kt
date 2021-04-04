package com.example.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity): Long

    @Query("SELECT * FROM product WHERE name LIKE '%' || :productName || '%' AND user_id = :userId")
    suspend fun getProductByUserId(productName: String?, userId: Long?): List<ProductEntity>

    @Query("SELECT * FROM product WHERE name LIKE '%' || :productName || '%'")
    suspend fun getProducts(productName: String?): List<ProductEntity>

    @Query("DELETE FROM product where user_id = :userId")
    suspend fun deleteAllProductByUser(userId: Int?)

    @Query("DELETE FROM product WHERE user_id = :userId")
    suspend fun deleteProductByUserId(userId: Int?)


}