package com.example.projetomobile1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetomobile1.data.model.Product
import com.example.projetomobile1.data.network.RetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    val createProductResult = MutableLiveData<Boolean>()
    val productsList = MutableLiveData<List<Product>>()
    val errorMessage = MutableLiveData<String>()

    fun createProduct(product: Product) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.createProduct(product)
                createProductResult.postValue(response.isSuccessful)
                if (!response.isSuccessful) {
                    errorMessage.postValue("Error creating product: ${response.code()}")
                }
            } catch (e: Exception) {
                createProductResult.postValue(false)
                errorMessage.postValue("Connection failure: ${e.message}")
                Log.e("API_ERROR", "Connection failure: ${e.message}")
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getProducts()
                if (response.isSuccessful) {
                    productsList.postValue(response.body())
                } else {
                    errorMessage.postValue("Error fetching products: ${response.code()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Connection failure: ${e.message}")
                Log.e("API_ERROR", "Connection failure: ${e.message}")
            }
        }
    }
}
