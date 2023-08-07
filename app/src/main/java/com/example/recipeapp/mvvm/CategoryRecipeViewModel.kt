package com.example.recipeapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.model.RecipeByCategory
import com.example.recipeapp.data.model.RecipeByCategoryList
import com.example.recipeapp.view.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRecipeViewModel:ViewModel() {
    val recipeLiveData= MutableLiveData<List<RecipeByCategory>>()

    fun getRecipeByCategory(categoryName:String){
        RetrofitInstance.api.getRecipeByCategory(categoryName).enqueue(object : Callback<RecipeByCategoryList?> {
            override fun onResponse(
                call: Call<RecipeByCategoryList?>,
                response: Response<RecipeByCategoryList?>
            ) {
                response.body()?.let { recipeList->
                   recipeLiveData.postValue(recipeList.meals)
                }

                }

            override fun onFailure(call: Call<RecipeByCategoryList?>, t: Throwable) {
               Log.e("recipe",t.message.toString())
            }
        })

    }

    fun observeRecipeLiveData():LiveData<List<RecipeByCategory>>{
        return recipeLiveData
    }
}