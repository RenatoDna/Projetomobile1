package com.example.projetomobile1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetomobile1.data.model.UserRequest
import com.example.projetomobile1.data.network.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    val loginResult = MutableLiveData<Boolean>()
    val registerResult = MutableLiveData<Boolean>()

    fun realizarLogin(email: String, pass: String){
        viewModelScope.launch {
            val response = RetrofitClient.apiService.login(UserRequest(email, pass))
            loginResult.value = response.isSuccessful
        }
    }

    fun registrar(email: String, pass: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.register(UserRequest(email, pass))
                // O postValue avisa a Activity que o resultado chegou
                registerResult.postValue(response.isSuccessful)
            } catch (e: Exception) {
                registerResult.postValue(false)
                Log.e("API_ERROR", "Falha na conexão: ${e.message}")
            }
        }
    }
}