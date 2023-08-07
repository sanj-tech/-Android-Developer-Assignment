package com.example.recipeapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.adapters.FavouriteRecipeAdapter
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.ActivityCartBinding
import com.example.recipeapp.databinding.ActivityRecipeDetailBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.mvvm.RecipeDeatilViewModel
import com.example.recipeapp.mvvm.RecipeViewModelFactory
import com.example.recipeapp.view.fragmentview.HomeFragment

class CartActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart)




    }
}













