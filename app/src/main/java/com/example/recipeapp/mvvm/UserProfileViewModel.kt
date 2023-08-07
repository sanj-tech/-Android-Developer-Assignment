package com.example.recipeapp.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.db.UserProfileDao
import com.example.recipeapp.data.model.UserProfile
import kotlinx.coroutines.launch

class UserProfileViewModel(private val repository: UserProfileDao) : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfile>()
   // private var user = recipeDB.recipeDao().getAllRecipe()

    fun saveUserProfile(userProfile: UserProfile) {
        viewModelScope.launch {
            repository.insertOrUpdate(userProfile)
        }
    }


}
