package com.example.recipeapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.data.model.MealList
import com.example.recipeapp.view.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDeatilViewModel(var recipeDB: RecipeDB) : ViewModel() {
    private var recipeDeatailLiveData = MutableLiveData<Meal>()


    fun RecipeDetail(id: String) {
        RetrofitInstance.api.getMealById(id).enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                if (response.body() != null) {
                    recipeDeatailLiveData.value = response.body()!!.meals[0]

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("MainActivity", t.message.toString())
            }
        })
    }

    fun observerRecipeDetailLiveData(): LiveData<Meal> {
        return recipeDeatailLiveData
    }

     fun insertRecipe(meal: Meal) {
        viewModelScope.launch {
            recipeDB.recipeDao().insertFavorite(meal)

        }
    }
        fun delete(meal: Meal){
            viewModelScope.launch {
                recipeDB.recipeDao().delete(meal)
            }
        }
    }

//}