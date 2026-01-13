package com.example.projetomobile1.data.network


import com.example.projetomobile1.data.model.LoginResponse
import com.example.projetomobile1.data.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body request: UserRequest): Response<LoginResponse>

    @POST("/register")
    suspend fun register(@Body request: UserRequest): Response<Unit>


}