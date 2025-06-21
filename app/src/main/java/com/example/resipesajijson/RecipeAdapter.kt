package com.example.resipesajijson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resipesajijson.model.DataItem

class RecipeAdapter (private val recipes: MutableList<DataItem>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
        val ivRecipeImage: ImageView = view.findViewById(R.id.ivRecipeImage)
        val tvIngredients: TextView = view.findViewById(R.id.tvIngredients)
        val tvInstructions: TextView = view.findViewById(R.id.tvInstructions)
        //val tvCuisine: TextView = view.findViewById(R.id.tvCuisine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.tvRecipeName.text = recipe.name
        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .into(holder.ivRecipeImage)

        // Baris untuk mengisi tvCuisine dihapus dari sini

        // Mengubah List<String> menjadi satu String dengan pemisah baris baru dan bullet point
        val ingredientsText = recipe.ingredients.joinToString(separator = "\n") { "• $it" }
        holder.tvIngredients.text = ingredientsText

        // Mengubah List<String> menjadi satu String dengan nomor
        val instructionsText = recipe.instructions.mapIndexed { index, instruction ->
            "${index + 1}. $instruction"
        }.joinToString(separator = "\n")
        holder.tvInstructions.text = instructionsText
    }

    override fun getItemCount() = recipes.size

    fun updateAll(newRecipes: List<DataItem>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}