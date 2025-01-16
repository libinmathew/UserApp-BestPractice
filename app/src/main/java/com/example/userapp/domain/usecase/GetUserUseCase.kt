package com.example.userapp.domain.usecase

import com.example.userapp.data.DataState
import com.example.userapp.domain.model.User
import com.example.userapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

fun interface GetUserUseCase {
    suspend operator fun invoke(): Flow<DataState<List<User>>>
}

class GetUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : GetUserUseCase {
    override suspend operator fun invoke(): Flow<DataState<List<User>>> = userRepository.getUser()
}