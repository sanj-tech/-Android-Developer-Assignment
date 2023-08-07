package com.example.recipeapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.UserProfile

@Database(entities = [Meal::class,UserProfile::class], version = 2, exportSchema = false)
@TypeConverters(RecipeTypeConverter::class)

abstract class RecipeDB : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        var instance: RecipeDB? = null

        @Synchronized
        fun getInstance(context: Context): RecipeDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context, RecipeDB::class.java, "recipeDb").allowMainThreadQueries()
                   .fallbackToDestructiveMigration().build()
            }
            return instance as RecipeDB
        }
    }
}