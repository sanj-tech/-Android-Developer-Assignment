package com.example.recipeapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapters.CategoryRecipeAdapter
import com.example.recipeapp.databinding.ActivityCategoryRecipeBinding
import com.example.recipeapp.mvvm.CategoryRecipeViewModel
import com.example.recipeapp.view.fragmentview.HomeFragment

class CategoryRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryRecipeBinding
    lateinit var categoryRecipeViewModel: CategoryRecipeViewModel
    lateinit var categoryRecipeAdapter: CategoryRecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_recipe)

        setCategoryItemInRv()

        categoryRecipeViewModel = ViewModelProviders.of(this)[CategoryRecipeViewModel::class.java]
        categoryRecipeViewModel.getRecipeByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        categoryRecipeViewModel.observeRecipeLiveData().observe(this, Observer { mealList ->
            binding.tvCategoryCount.text = mealList.size.toString()
            categoryRecipeAdapter.setRecipeList(mealList)

            mealList.forEach {
                Log.d("test", it.strMeal)
            }
        })
    }

    private fun setCategoryItemInRv() {
        categoryRecipeAdapter = CategoryRecipeAdapter()
        binding.recipeRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categoryRecipeAdapter
        }
    }
}