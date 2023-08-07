package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.CustomCategoryRecipeItemViewBinding

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    //lateinit var onClick: ((Meal) -> Unit)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartAdapter.CartViewHolder(
            CustomCategoryRecipeItemViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        this.differ.currentList[position]
        Glide.with(holder.itemView).load(recipe.strMealThumb).into(holder.binding.categoryImage)
        holder.binding.tvCategoryName.text = recipe.strMeal

        holder.itemView.setOnClickListener({
            //
        // onClick.invoke(recipe)

        })
    }


    class CartViewHolder(val binding: CustomCategoryRecipeItemViewBinding):RecyclerView.ViewHolder(binding.root) {

    }
}