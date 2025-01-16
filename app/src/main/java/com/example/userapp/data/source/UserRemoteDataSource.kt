package com.example.userapp.data.source

import com.example.userapp.data.api.ApiService
import com.example.userapp.data.api.model.UserDto
import com.example.userapp.data.api.model.UsersResponseDto
import retrofit2.Response
import javax.inject.Inject

interface UserRemoteDataSource : UserDataSource<UserDto> {
    suspend fun fetchUsers(): Response<UsersResponseDto>

}

class UserRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    UserRemoteDataSource {
    override suspend fun fetchUsers(): Response<UsersResponseDto> {
        return apiService.getUsers()
    }

    override suspend fun getUser(id: Int): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: UserDto) {
        TODO("Not yet implemented")
    }

}