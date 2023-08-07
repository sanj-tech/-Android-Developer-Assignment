package com.example.recipeapp.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.data.db.RecipeDB

class RecipeViewModelFactory(private val recipeDB: RecipeDB):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDeatilViewModel(recipeDB)as T
    }
}