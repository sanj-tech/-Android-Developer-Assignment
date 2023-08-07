package com.example.recipeapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.recipeapp.R
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.mvvm.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val viewModel: HomeViewModel by lazy {

        val reccipeDb = RecipeDB.getInstance(this)
        val homeViewModelFactory = HomeViewModelFactory(reccipeDb)
        ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = Navigation.findNavController(this, R.id.frag_host)
        NavigationUI.setupWithNavController(bottomNavigation, navController)


    }
}