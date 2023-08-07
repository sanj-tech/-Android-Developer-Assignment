package com.example.recipeapp.view

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    var retrofit: Retrofit? =null
//    fun getRetrofitInstance(): Retrofit {
//        if (retrofit==null){
//            retrofit= Retrofit.Builder()
//                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
//                .addConverterFactory(GsonConverterFactory.create())
//
//                .build()
//        }
//        return retrofit!!
//    }

//second way to create Retrofit instance

    val api:FoodApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApi::class.java)
    }
}