package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.data.model.Category
import com.example.recipeapp.data.model.CategoryList
import com.example.recipeapp.databinding.CategoryCardBinding
import com.example.recipeapp.databinding.CustomCategoryItemViewBinding
import com.example.recipeapp.databinding.FragmentHomeBinding

class CategoriesRecyclerAdapter():
    RecyclerView.Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder>() {
    private var categoryList=ArrayList<Category>()
     var onItemClick:((Category)->Unit)?=null

    fun setCategoryList(categoryList: List<Category>){
        this.categoryList=categoryList as ArrayList<Category>
notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(CustomCategoryItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Glide.with(holder.itemView).load(categoryList[position].strCategoryThumb).into(holder.binding.cateImage)
        holder.binding.tvItemName.text=categoryList[position].strCategory
        holder.itemView.setOnClickListener({
            onItemClick!!.invoke(categoryList[position])
        })

    }



    class CategoriesViewHolder(val binding: CustomCategoryItemViewBinding):RecyclerView.ViewHolder(binding.root) {

    }
}