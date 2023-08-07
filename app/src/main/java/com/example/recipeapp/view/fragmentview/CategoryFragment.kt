package com.example.recipeapp.view.fragmentview

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapters.CategoriesRecyclerAdapter
import com.example.recipeapp.adapters.CategoryRecipeAdapter
import com.example.recipeapp.adapters.FavouriteRecipeAdapter
import com.example.recipeapp.databinding.FragmentCategoryBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.view.CategoryRecipeActivity
import com.example.recipeapp.view.MainActivity


class CategoryFragment : Fragment() {
    private lateinit var binding:FragmentCategoryBinding
private lateinit var categoryRecyclerAdapter: CategoriesRecyclerAdapter
lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCategoryBinding.inflate(inflater)
        return binding.root
        //return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PrepareRecyclerItem()
        observerCategories()
        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoryRecyclerAdapter.onItemClick = { category ->
            val intent = Intent(activity, CategoryRecipeActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)

        }
    }

    private fun observerCategories() {
        viewModel.observeCategoryByLiveData().observe(viewLifecycleOwner, Observer {category->
           categoryRecyclerAdapter.setCategoryList(category)
        })
    }

    private fun PrepareRecyclerItem() {
        categoryRecyclerAdapter=CategoriesRecyclerAdapter()
        binding.categoryRv.apply {
            layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter=categoryRecyclerAdapter
        }
    }


}