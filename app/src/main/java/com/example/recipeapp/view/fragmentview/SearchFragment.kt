package com.example.recipeapp.view.fragmentview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapters.FavouriteRecipeAdapter
import com.example.recipeapp.databinding.FragmentSearchBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.view.MainActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var searchRecyclerViewAdapter:FavouriteRecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(inflater)
        return binding.root
       // return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItemInSearcRecyclerView()
        binding.ivSearch.setOnClickListener{
            searchRecipe()
        }
        observeSearchRecipeLiveData()

//        var search:Job?=null
//        binding.etSearch.setOnClickListener({searchQuery->
//            search?.cancel()
//            search=lifecycleScope.launch{
//                delay(100)
//                viewModel.searchRecipe(searchQuery.toString())
//            }
//        })
        puttinngInputShowSearchResult()
    }

    private fun puttinngInputShowSearchResult() {
        var search:Job?=null
        binding.etSearch.addTextChangedListener{searchQuery->
            search?.cancel()
            search=lifecycleScope.launch{
                delay(100)
                viewModel.searchRecipe(searchQuery.toString())
            }
        }

    }


    private fun observeSearchRecipeLiveData() {
        viewModel.observeSearchRecipeLiveData().observe(viewLifecycleOwner, Observer {recipeList->
        searchRecyclerViewAdapter.differ.submitList(recipeList)

        })
    }

    private fun searchRecipe() {
        var searchQuery=binding.etSearch.text.toString()
        if (searchQuery.isNotEmpty()){
            viewModel.searchRecipe(searchQuery)
        }
    }

    private fun setItemInSearcRecyclerView() {
        searchRecyclerViewAdapter= FavouriteRecipeAdapter()
        binding.searchRecyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=searchRecyclerViewAdapter
        }
    }

}