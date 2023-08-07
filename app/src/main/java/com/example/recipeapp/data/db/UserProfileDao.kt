package com.example.recipeapp.data.db

import androidx.room.*
import com.example.recipeapp.data.model.UserProfile

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(userProfile: UserProfile)

//    @Query("SELECT * FROM user_profile")
//    suspend fun getUserProfile(): UserProfile?
//
//    @Update
//    suspend fun update(userProfile: UserProfile)
}