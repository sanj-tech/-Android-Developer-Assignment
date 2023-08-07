package com.example.recipeapp.view


import com.example.recipeapp.data.model.CategoryList
import com.example.recipeapp.data.model.RecipeByCategoryList
import com.example.recipeapp.data.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApi {
    //End Url
    @GET ("random.php")
    fun getRandomMeal():Call<MealList>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id:String):Call<MealList>

//
    @GET("filter.php?")
    fun getFamousItem(@Query("c") categoryName:String):Call<RecipeByCategoryList>


    //
// Process-step1 copy json and paste in to model class,-Goto Food api,create function,then goes to viewmodel class create another function,then
    // create declare one variable,category LiveData,create one function getCategory(),in this function call retofit instance,then create another function
    // to observe live data,then go home fragment,and create one function

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getRecipeByCategory(@Query("c")categoryName: String):Call<RecipeByCategoryList>
//

//
    @GET("search.php?")
    fun searchRecipe(@Query("s") searchQuery:String):Call<MealList>
}