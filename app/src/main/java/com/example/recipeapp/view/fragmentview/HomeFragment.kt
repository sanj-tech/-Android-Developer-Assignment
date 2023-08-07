package com.example.recipeapp.view.fragmentview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.adapters.CategoriesRecyclerAdapter
import com.example.recipeapp.adapters.FamousRecipeAdapter
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.view.CategoryRecipeActivity
import com.example.recipeapp.view.MainActivity
import com.example.recipeapp.view.RecipeDetailActivity

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var famousRecipeAdapter: FamousRecipeAdapter

    private lateinit var categoriesRecyclerAdapter: CategoriesRecyclerAdapter

    companion object {
        const val MEAL_ID = "com.example.recipeapp.view.fragmentview.idMeal"
        const val MEAL_NAME = "com.example.recipeapp.view.fragmentview.mealName"
        const val MEAL_THUMB = "com.example.recipeapp.view.fragmentview"
        const val CATEGORY_NAME = "com.example.recipeapp.view.fragmentview\""

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
       // homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        famousRecipeAdapter = FamousRecipeAdapter()
        categoriesRecyclerAdapter = CategoriesRecyclerAdapter()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItemINRecyclerView()
        setItemInCategoryRecyclerView()

        viewModel.getRandomMeal()

        observeRandomMeal()
        onRandomMealClick()

        viewModel.getFamousItem()
        observeFamousItemLiveData()
        famousItemClick()

        viewModel.getCategories()
        observeCatehoryLiveData()

        onCategoryClick()

        onSearchImageClick()

//* below code fetch json data without using mvvm

// Second way to call api
//        super.onViewCreated(view, savedInstanceState)
//        val api = RetrofitInstance.getRetrofitInstance().create(FoodApi::class.java)
//        val call = api.getRandomMeal()
//        call.enqueue(object : Callback<MealList?> {
//            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
//                if (response.body() != null) {
//                    val meal = response.body()!!.meals[0]
////Show the image into imageview
//                    Glide.with(this@HomeFragment).load(meal.strMealThumb)
//                        .into(binding.imgRandomMeal)
//                    // Log.d("Test","meal id ${meal.idMeal} name ${meal.strMeal}")
//
//                }
//
//


// First way to call Api
//        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
//            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
//                if (response.body() != null) {
//                    val meal = response.body()!!.meals[0]
////Show the image into imageview
//                    Glide.with(this@HomeFragment).load(meal.strMealThumb)
//                        .into(binding.imgRandomMeal)
//
//                    //show result in logcat
//                    // Log.d("Test","meal id ${meal.idMeal} name ${meal.strMeal}")
//
//                }else{
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<MealList?>, t: Throwable) {
//                Log.d("Meal Fragment", t.message.toString())
//            }
//        })

        //*
    }

    private fun onSearchImageClick() {
        binding.ivSearch.setOnClickListener{
           findNavController().navigate(R.id.action_homeFragment_to_searchFragment2)
        }
    }

    private fun onCategoryClick() {
        categoriesRecyclerAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryRecipeActivity::class.java)
            intent.putExtra(CATEGORY_NAME, category.strCategory)
            startActivity(intent)

        }
    }

    private fun setItemInCategoryRecyclerView() {
        binding.categoryRecyclerView.apply {

            adapter = categoriesRecyclerAdapter
        }
    }

    private fun observeCatehoryLiveData() {
        viewModel.observeCategoryByLiveData()
            .observe(viewLifecycleOwner, Observer { categories ->

                categories.forEach {
                    Log.d("test", it.strCategory)
                    categoriesRecyclerAdapter.setCategoryList(categories)
                }
            })
    }

    private fun famousItemClick() {
        famousRecipeAdapter.onClick = {
            val intent = Intent(activity, RecipeDetailActivity::class.java)
            intent.putExtra(MEAL_ID, it.idMeal)
            intent.putExtra(MEAL_NAME, it.strMeal)
            intent.putExtra(MEAL_THUMB, it.strMealThumb)

            startActivity(intent)

        }
    }

    private fun setItemINRecyclerView() {
        binding.famousRv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = famousRecipeAdapter
        }
    }

    private fun observeFamousItemLiveData() {
        viewModel.observeFamousItemLiveData().observe(
            viewLifecycleOwner
        ) { famousList ->
            famousRecipeAdapter.setMeals(famousList = famousList as ArrayList)
        }
    }

    private fun onRandomMealClick() {
        binding.imgRandomMeal.setOnClickListener({
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        })

    }

    private fun observeRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner,
            {

                Glide.with(this@HomeFragment).load(it!!.strMealThumb)
                    .into(binding.imgRandomMeal)

                this.randomMeal = it


            })

    }


}


