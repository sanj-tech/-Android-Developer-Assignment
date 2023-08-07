package com.example.recipeapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipeapp.data.model.Meal

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertFavorite(meal: Meal)

    @Delete
     fun delete(recipe: Meal)

    @Query("Select *from recipeInfo")
      fun getAllRecipe(): LiveData<List<Meal>>

}