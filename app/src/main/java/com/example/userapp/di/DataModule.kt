package com.example.userapp.di

import android.content.Context
import androidx.room.Room
import com.example.userapp.BuildConfig
import com.example.userapp.data.api.ApiService
import com.example.userapp.data.db.AppDatabase
import com.example.userapp.data.db.dao.UserDao
import com.example.userapp.data.source.UserLocalDataSource
import com.example.userapp.data.source.UserLocalDataSourceImpl
import com.example.userapp.data.source.UserRemoteDataSource
import com.example.userapp.data.source.UserRemoteDataSourceImpl
import com.example.userapp.common.utils.ErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    @Named("API_URL")
    fun provideApiUrl(): String {
        return BuildConfig.base_url
    }

    @Provides
    @Singleton
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ErrorInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("API_URL") apiUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserLocalDataSource(userDao: UserDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao)
    }

    @Provides
    fun provideUserRemoteDataSource(apiService: ApiService): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(apiService)
    }
}