package com.example.userapp.domain.repository

import com.example.userapp.data.DataState
import com.example.userapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository { suspend fun getUser():Flow<DataState<List<User>>>}