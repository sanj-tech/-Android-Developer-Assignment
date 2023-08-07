package com.example.recipeapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.data.model.*
import com.example.recipeapp.view.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val recipeDB: RecipeDB):ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var famousItemLiveData = MutableLiveData<List<RecipeByCategory>>()
    private var categoryLiveData = MutableLiveData<List<Category>>()

    private var searchRecipeLiveData = MutableLiveData<List<Meal>>()

    //implementation of room
    private var favouritRecipeLiveData = recipeDB.recipeDao().getAllRecipe()

    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                if (response.body() != null) {
                    val meal = response.body()!!.meals[0]
                    randomMealLiveData.value = meal


                    //show result in logcat
                    // Log.d("Test","meal id ${meal.idMeal} name ${meal.strMeal}")

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("Meal Fragment", t.message.toString())
            }
        })
    }

    fun getFamousItem() {
        RetrofitInstance.api.getFamousItem("Seafood")
            .enqueue(object : Callback<RecipeByCategoryList?> {
                override fun onResponse(
                    call: Call<RecipeByCategoryList?>,
                    response: Response<RecipeByCategoryList?>
                ) {
                    if (response.body() != null) {
                        famousItemLiveData.value = response.body()!!.meals
                    }
                }

                override fun onFailure(call: Call<RecipeByCategoryList?>, t: Throwable) {
                    Log.d("HomeFrag,ent", t.message.toString())
                }
            })
    }


    fun getCategories() {
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList?> {
            override fun onResponse(call: Call<CategoryList?>, response: Response<CategoryList?>) {
                if (response.body() != null) {
                    categoryLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<CategoryList?>, t: Throwable) {
                Log.d("HomeViewmodel", t.message.toString())
            }
        })
    }

    fun delete(meal: Meal) {
        viewModelScope.launch {
            recipeDB.recipeDao().delete(meal)
        }
    }

    fun insertRecipe(meal: Meal) {
        viewModelScope.launch {
            recipeDB.recipeDao().insertFavorite(meal)

        }
    }

    fun searchRecipe(searchQuery: String) =
        RetrofitInstance.api.searchRecipe(searchQuery).enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {

                val recipeList = response.body()?.meals
                recipeList.let {
                    searchRecipeLiveData.postValue(it)
                }

            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    fun observeSearchRecipeLiveData():LiveData<List<Meal>>{
        return searchRecipeLiveData
    }




    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData

    }

    fun observeFamousItemLiveData():LiveData<List<RecipeByCategory>>{
        return famousItemLiveData

    }
    fun observeCategoryByLiveData():LiveData<List<Category>>{
        return categoryLiveData

    }

    fun observeFavouriteRecipeLiveData():LiveData<List<Meal>>{
        return favouritRecipeLiveData
    }

    fun observeCartRecipeLiveData():LiveData<List<Meal>>{
        return favouritRecipeLiveData
    }



}