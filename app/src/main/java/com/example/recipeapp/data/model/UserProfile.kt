package com.example.recipeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
                       val id: Long = 0,
                       val firstName: String,
                       val lastName: String,
                       )