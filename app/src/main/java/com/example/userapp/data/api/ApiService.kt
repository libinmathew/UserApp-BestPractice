package com.example.userapp.data.api

import com.example.userapp.data.api.model.UsersResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    suspend fun getUsers(): Response<UsersResponseDto>
}


