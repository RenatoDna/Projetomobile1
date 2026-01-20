package com.example.projetomobile1.data.network

import android.content.Context
import com.example.projetomobile1.data.network.Prefs
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private var apiService: ApiService? = null

    fun getApiService(context: Context): ApiService {
        return apiService ?: synchronized(this) {
            val baseUrl = Prefs.getUrl(context)
            val instance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
            apiService = instance
            instance

        }
    }

    fun resetClient(){
        apiService = null
    }
}