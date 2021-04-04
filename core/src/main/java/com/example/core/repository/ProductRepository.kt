package com.example.core.repository

import com.example.core.dao.ProductDao
import com.example.core.entities.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val productDao: ProductDao?) : ProductContract {

    override suspend fun insert(productEntity: ProductEntity): Flow<Long?> {
        return flow { this.emit(productDao?.insert(productEntity)) }
    }

    override suspend fun getProduct(productName: String?, userId: Long?): Flow<List<ProductEntity>?> {
        return flow { this.emit(productDao?.getProductByUserId(productName, userId)) }
    }

    override suspend fun deleteAllProductByUser(userId: Int?) {
        productDao?.deleteAllProductByUser(userId)
    }

    override suspend fun deleteProductByUserId(userId: Int?) {
        productDao?.deleteProductByUserId(userId)
    }


}