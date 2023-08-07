package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.RecipeByCategory
import com.example.recipeapp.databinding.CustomCategoryRecipeItemViewBinding

class CategoryRecipeAdapter:
    RecyclerView.Adapter<CategoryRecipeAdapter.CategoryRecipeViewHolder>() {

    private var recipeList=ArrayList<RecipeByCategory>()

    fun setRecipeList(recipeList: List<RecipeByCategory>)
    {
        this.recipeList=recipeList as ArrayList<RecipeByCategory>
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryRecipeAdapter.CategoryRecipeViewHolder {
       return CategoryRecipeViewHolder(CustomCategoryRecipeItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: CategoryRecipeAdapter.CategoryRecipeViewHolder,
        position: Int
    ) {
       Glide.with(holder.itemView).load(recipeList[position].strMealThumb).into(holder.binding.categoryImage)
        holder.binding.tvCategoryName.text=recipeList[position].strMeal
    }

    override fun getItemCount(): Int {
       return recipeList.size
    }
    class CategoryRecipeViewHolder(val binding: CustomCategoryRecipeItemViewBinding):RecyclerView.ViewHolder(binding.root) {

    }

}