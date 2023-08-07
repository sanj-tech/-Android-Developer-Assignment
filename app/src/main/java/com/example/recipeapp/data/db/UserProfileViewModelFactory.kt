package com.example.recipeapp.data.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeapp.mvvm.UserProfileViewModel

class UserProfileViewModelFactory(private val repository: UserProfileDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repository) as T


    }
}

