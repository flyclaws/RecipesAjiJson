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
        /**
         * ViewHolder berisi referensi ke view-view di dalam setiap item layout (item_list_recipe.xml).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
            val ivRecipeImage: ImageView = view.findViewById(R.id.ivRecipeImage)
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
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivRecipeImage)
        }

        override fun getItemCount() = recipes.size

        /**
         * Fungsi untuk menambah satu resep baru ke dalam daftar.
         */
        fun addRecipe(newRecipe: DataItem) {
            recipes.add(newRecipe)
            notifyItemInserted(recipes.lastIndex) // Animasi yang lebih efisien
        }

        /**
         * Fungsi untuk mengganti seluruh data dengan yang baru.
         * Ini berguna saat pertama kali memuat data atau saat melakukan refresh.
         */
        fun updateAll(newRecipes: List<DataItem>) {
            recipes.clear()
            recipes.addAll(newRecipes)
            notifyDataSetChanged()
        }

        /**
         * Fungsi untuk menghapus semua data dari daftar.
         */
        fun clear() {
            recipes.clear()
            notifyDataSetChanged()
        }
}