package com.example.userapp.data.source

interface UserDataSource<T> {
    suspend fun getUser(id: Int): T
    suspend fun saveUser(user: T)
}