package com.example.recipeapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.data.db.RecipeDB
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.ActivityRecipeDetailBinding
import com.example.recipeapp.mvvm.RecipeDeatilViewModel
import com.example.recipeapp.mvvm.RecipeViewModelFactory
import com.example.recipeapp.view.fragmentview.HomeFragment

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeId: String
    private lateinit var recipeName: String
    private lateinit var recipeImage: String
    private lateinit var youTubeLink: String
    private lateinit var recipeDeatilViewModel: RecipeDeatilViewModel
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_detail)

        //this one for room
        val recipeDB = RecipeDB.getInstance(this)
        val recipeViewModelFactory = RecipeViewModelFactory(recipeDB)
        recipeDeatilViewModel =
            ViewModelProvider(this, recipeViewModelFactory)[RecipeDeatilViewModel::class.java]

        //this one for retrofit
//        recipeDeatilViewModel= ViewModelProviders.of(this)[RecipeDeatilViewModel::class.java]


        getRecipeDetail()
        setRecipeDetail()
        loadingCase()
        recipeDeatilViewModel.RecipeDetail(recipeId)
        observeRecipeDetailLiveData()
        onYouTubeData()
        onFavouriteItemClick()
        onCartItemClick()

    }

    private fun onCartItemClick() {
        binding.btnSave.backgroundTintList = resources.getColorStateList(R.color.green)
        saveRecipe?.let { meal ->

            recipeDeatilViewModel.insertRecipe(meal)


        }
    }


    private fun onFavouriteItemClick() {
        binding.btnFavourite.setOnClickListener({
            binding.btnFavourite.backgroundTintList = resources.getColorStateList(R.color.red)
            saveRecipe?.let { meal ->

                recipeDeatilViewModel.insertRecipe(meal)


                Toast.makeText(this, "favourite", Toast.LENGTH_LONG).show()

            }
        })
    }


    private var saveRecipe: Meal? = null

    private fun onYouTubeData() {
        binding.imgYoutube.setOnClickListener({

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        })
    }


    private fun observeRecipeDetailLiveData() {
        recipeDeatilViewModel.observerRecipeDetailLiveData().observe(this, object : Observer<Meal> {
            override fun onChanged(t: Meal?) {
                onResponse()
                var recipe = t

                //for room
                saveRecipe = recipe

                binding.tvCategoryInfo.text = " Category: ${recipe!!.strCategory}"
                binding.tvAreaInfo.text = "Location:${recipe!!.strArea}"
                binding.tvContent.text = recipe!!.strInstructions
                binding.tvIngredient.text = recipe!!.strIngredient1
                binding.tvIngredient2.text = recipe!!.strIngredient2
                binding.tvIngredient3.text = recipe!!.strIngredient3
                binding.tvIngredient4.text = recipe!!.strIngredient4
                binding.tvIngredient5.text = recipe!!.strIngredient5
                binding.tvIngredient6.text = recipe!!.strIngredient6
                binding.tvIngredient7.text = recipe!!.strIngredient7
                youTubeLink = recipe.strYoutube
            }

        })
    }

    private fun setRecipeDetail() {
        Glide.with(applicationContext).load(recipeImage).into(binding.imgMealDetail)

        binding.collapsingToolbar.title = recipeName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getRecipeDetail() {
        val intent = intent
        recipeId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        recipeName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        recipeImage = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFavourite.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategoryInfo.visibility = View.INVISIBLE
        binding.tvAreaInfo.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponse() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnFavourite.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }


}