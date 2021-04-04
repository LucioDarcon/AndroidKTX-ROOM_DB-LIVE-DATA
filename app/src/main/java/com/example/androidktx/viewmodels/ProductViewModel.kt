package com.example.androidktx.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.converter.ProductConverter.converterProductToProductEntity
import com.example.androidktx.data.model.Product
import com.example.androidktx.data.model.User
import com.example.core.entities.ProductEntity
import com.example.core.entities.UserEntity
import com.example.core.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository, search: String?, private val userId: Long? = null) : ViewModel() {

    var productLiveData : MutableLiveData<List<ProductEntity>?> = MutableLiveData()
    var productCreated                                          = MutableLiveData<Product>()
    private var mSearch: String?                                = search ?: ""

    init {
        getProducts()
    }

    private fun getProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.getProduct(mSearch, userId).collect { productList ->
                productLiveData.postValue(productList)
            }
        }
    }

    fun createProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.insert(converterProductToProductEntity(product)).collect {
                productCreated.postValue(product)
            }
        }
    }

    class ProductViewModelFactory(private val productRepository: ProductRepository,
                                  private val search: String?,
                                  private val userId: Long? = null) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProductViewModel(productRepository, search, userId) as T
        }


    }

}