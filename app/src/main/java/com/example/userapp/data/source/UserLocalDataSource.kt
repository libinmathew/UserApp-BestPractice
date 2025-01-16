package com.example.userapp.data.source;

import com.example.userapp.data.db.dao.UserDao;
import com.example.userapp.data.db.entity.UserEntity

import javax.inject.Inject;

interface UserLocalDataSource : UserDataSource<UserEntity> {
    suspend fun clearCache()
}
class UserLocalDataSourceImpl @Inject
constructor(private val userDao:UserDao) : UserLocalDataSource {

    override suspend fun clearCache() {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: Int): UserEntity {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: UserEntity) {

    }
}