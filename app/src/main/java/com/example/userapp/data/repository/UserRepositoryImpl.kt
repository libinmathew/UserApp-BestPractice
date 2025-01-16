package com.example.userapp.data.repository

import com.example.userapp.data.AppError
import com.example.userapp.data.DataState
import com.example.userapp.data.api.model.toDomainModel
import com.example.userapp.data.source.UserLocalDataSource
import com.example.userapp.data.source.UserRemoteDataSource
import com.example.userapp.domain.model.User
import com.example.userapp.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUser(): Flow<DataState<List<User>>> = flow {
        val response = userRemoteDataSource.fetchUsers()
        if (response.isSuccessful) {
            emit(DataState.Success(response.body()?.data!!.map {
                it.toDomainModel()
            }))
        } else {
            emit(
                DataState.Failure(error = AppError.NetworkError.UnknownError("Something went wrong"))
            )
        }

    }.flowOn(Dispatchers.IO).catch {
        DataState.Failure(
            error = AppError.NetworkError.UnknownError("Something went wrong")
        )
    }

}