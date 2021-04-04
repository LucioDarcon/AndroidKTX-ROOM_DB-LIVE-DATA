package com.example.androidktx.converter

import com.example.androidktx.data.model.Product
import com.example.core.entities.ProductEntity

object ProductConverter {

    fun converterProductEntityToProduct(productEntityList: List<ProductEntity>?): List<Product>? {
        return productEntityList?.map { Product(id = it.id, name = it.name, description = it.description, price = it.price, userId = it.userId) }
    }

    fun converterProductToProductEntity(product: Product): ProductEntity {
        return ProductEntity(id = product.id, name = product.name, description = product.description, price = product.price, userId = product.userId)
    }

}