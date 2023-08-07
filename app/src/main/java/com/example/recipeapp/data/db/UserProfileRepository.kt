package com.example.recipeapp.data.db

import com.example.recipeapp.data.model.UserProfile

class UserProfileRepository(private val userDao: UserProfileDao) {
     fun insertOrUpdate(userProfile: UserProfile) {
        userDao.insertOrUpdate(userProfile)
    }

//    suspend fun getUserProfile(): UserProfile? {
//        return userDao.getUserProfile()
//    }
}