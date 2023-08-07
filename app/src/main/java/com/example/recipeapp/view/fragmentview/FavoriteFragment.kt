package com.example.recipeapp.view.fragmentview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.adapters.CartAdapter
import com.example.recipeapp.adapters.FavouriteRecipeAdapter
import com.example.recipeapp.databinding.FragmentFavoriteBinding
import com.example.recipeapp.mvvm.HomeViewModel
import com.example.recipeapp.view.MainActivity
import com.google.android.material.snackbar.Snackbar


class FavoriteFragment() : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favouriteRecipeAdapter: FavouriteRecipeAdapter
    private lateinit var cartAdapter: CartAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        favouriteRecipeAdapter = FavouriteRecipeAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_favorite_meal_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeFavourite()
        setFavouriteItemInRecyclerView()


        observeCart()
        setCartItemInRecyclerView()

        //  AddToCart()


        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.delete(favouriteRecipeAdapter.differ.currentList[position])
                Snackbar.make(requireView(), "meal deleted", Snackbar.LENGTH_SHORT).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.insertRecipe(favouriteRecipeAdapter.differ.currentList[position])
                    }
                ).show()
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.favRv)

//

        val itemTouchListener = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.delete(cartAdapter.differ.currentList[position])
                Snackbar.make(requireView(), "meal deleted", Snackbar.LENGTH_SHORT).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.insertRecipe(cartAdapter.differ.currentList[position])
                    }
                ).show()
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.cartItemRv)


    }

    private fun setCartItemInRecyclerView() {
        cartAdapter = CartAdapter()
        binding.cartItemRv.apply {
            //layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = cartAdapter
        }
    }

    private fun observeCart() {
        viewModel.observeCartRecipeLiveData().observe(viewLifecycleOwner, Observer { meal ->

            cartAdapter.differ.submitList(meal)
        })
    }

    private fun setFavouriteItemInRecyclerView() {
        favouriteRecipeAdapter = FavouriteRecipeAdapter()

        binding.favRv.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favouriteRecipeAdapter
        }
    }

    private fun observeFavourite() {
        viewModel.observeFavouriteRecipeLiveData().observe(viewLifecycleOwner, Observer { meal ->

            favouriteRecipeAdapter.differ.submitList(meal)
//            recipe.forEach{
//                Log.d("helloTest",it.idMeal)
//            }
        })
    }

//    private fun AddToCart() {
//        favouriteRecipeAdapter.onClick = {
//            val intent = Intent(activity, CartActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(context, "cart", Toast.LENGTH_LONG).show()
//
//        }
//    }


}