package com.example.userapp


import com.example.userapp.data.api.ApiService
import com.example.userapp.data.api.model.SupportDto
import com.example.userapp.data.api.model.UsersResponseDto
import com.example.userapp.data.source.UserRemoteDataSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response


class UserRemoteDataSourceImplTest {
    private val apiService: ApiService = mockk()

    @Test
    fun fetchUsers_shouldCallApi() = runBlocking {
        val userRemoteDataSource = UserRemoteDataSourceImpl(apiService)
        val mockResponse = Response.success(UsersResponseDto(emptyList(),
            page = 0,
            perPage = 0,
            support = SupportDto(text = "", url = ""),
            total = 0,
            totalPages = 0
        ))
        coEvery { apiService.getUsers() } returns mockResponse

        // Act
        val response = userRemoteDataSource.fetchUsers()

        // Assert
        coVerify (exactly = 1) { apiService.getUsers() }
        assertEquals(mockResponse, response)
    }
}