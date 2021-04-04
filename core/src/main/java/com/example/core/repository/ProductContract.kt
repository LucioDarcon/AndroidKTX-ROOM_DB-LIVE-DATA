package com.example.core.repository

import com.example.core.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductContract {

    suspend fun insert(productEntity: ProductEntity): Flow<Long?>

    suspend fun getProduct(productName: String?, userId: Long?): Flow<List<ProductEntity>?>

    suspend fun deleteAllProductByUser(userId: Int?)

    suspend fun deleteProductByUserId(userId: Int?)


}