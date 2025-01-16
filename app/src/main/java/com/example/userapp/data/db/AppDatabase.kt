package com.example.userapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userapp.data.db.dao.UserDao
import com.example.userapp.data.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}