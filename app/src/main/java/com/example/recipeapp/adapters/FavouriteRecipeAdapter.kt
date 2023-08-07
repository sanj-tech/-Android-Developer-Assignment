package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.Meal
import com.example.recipeapp.databinding.CustomCategoryRecipeItemViewBinding

class FavouriteRecipeAdapter() :
    RecyclerView.Adapter<FavouriteRecipeAdapter.FavouriteRecipeViewHolder>() {

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


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteRecipeAdapter.FavouriteRecipeViewHolder {

        return FavouriteRecipeViewHolder(
            CustomCategoryRecipeItemViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    }

    override fun onBindViewHolder(
        holder: FavouriteRecipeAdapter.FavouriteRecipeViewHolder, position: Int
    ) {

//        if (getItemViewType(position) == VIEW_TYPE_CART) {
//            val recipe = differ.currentList[position]
//            this.differ.currentList[position]
//            Glide.with(holder.itemView).load(recipe.strMealThumb).into(holder.binding.categoryImage)
//            holder.binding.tvCategoryName.text = recipe.strMeal
//            holder.itemView.setOnClickListener({
//                onClick.invoke(recipe)
//
//            })
//            // Bind data for cart view
//        } else if (getItemViewType(position) == VIEW_TYPE_FAVORITE) {
//
//            val recipe = differ.currentList[position]
//            this.differ.currentList[position]
//            Glide.with(holder.itemView).load(recipe.strMealThumb).into(holder.binding.categoryImage)
//            holder.binding.tvCategoryName.text = recipe.strMeal
//            holder.itemView.setOnClickListener({
//                onClick.invoke(recipe)
//
//            })
//
//            // Bind data for favorite view
//        }


        val recipe = differ.currentList[position]
        this.differ.currentList[position]
        Glide.with(holder.itemView).load(recipe.strMealThumb).into(holder.binding.categoryImage)
        holder.binding.tvCategoryName.text = recipe.strMeal

        holder.itemView.setOnClickListener({
            //onClick.invoke(recipe)

        })

    }
//    override fun getItemViewType(position: Int): Int {
//        // Return appropriate view type based on position or other logic
//        return if (position % 2 == 0) VIEW_TYPE_CART else VIEW_TYPE_FAVORITE
//    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class FavouriteRecipeViewHolder(val binding: CustomCategoryRecipeItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}