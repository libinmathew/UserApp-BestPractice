package com.example.userapp.di

import com.example.userapp.data.repository.UserRepositoryImpl
import com.example.userapp.domain.repository.UserRepository
import com.example.userapp.domain.usecase.GetUserUseCase
import com.example.userapp.domain.usecase.GetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainProvidesModule {
    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCaseImpl(userRepository)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainBindsModule {
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}