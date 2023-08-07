package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.RecipeByCategory
import com.example.recipeapp.databinding.FamousRecipeBinding

class FamousRecipeAdapter(): RecyclerView.Adapter<FamousRecipeAdapter.FamousRecipeViewHolder>() {
    lateinit var onClick: ((RecipeByCategory)->Unit)
    var famousList=ArrayList<RecipeByCategory>()

    fun setMeals(famousList: ArrayList<RecipeByCategory>){
        this.famousList=famousList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamousRecipeViewHolder {
        return FamousRecipeViewHolder(FamousRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
     return famousList.size
    }

    override fun onBindViewHolder(holder: FamousRecipeViewHolder, position: Int) {
       Glide.with(holder.itemView).load(famousList[position].strMealThumb).into(holder.binding.famousRecipe)
        holder.binding.tvRecipeName.text=famousList[position].strMeal

        holder.itemView.setOnClickListener({
           onClick.invoke(famousList[position])

        })
    }




    class FamousRecipeViewHolder(var binding:FamousRecipeBinding):RecyclerView.ViewHolder(binding.root) {

    }
}


