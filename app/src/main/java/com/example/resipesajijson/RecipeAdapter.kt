package com.example.resipesajijson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resipesajijson.model.DataItem

class RecipeAdapter(private var recipes: MutableList<DataItem>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    /**
     * ViewHolder menyimpan referensi ke setiap view di dalam satu item kartu.
     * Ini membuat akses menjadi lebih cepat.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvRecipeName: TextView = view.findViewById(R.id.tvRecipeName)
        val ivRecipeImage: ImageView = view.findViewById(R.id.ivRecipeImage)
        val tvCuisine: TextView = view.findViewById(R.id.tvCuisine)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val btnDetail: Button = view.findViewById(R.id.btnDetail)
    }

    /**
     * Membuat ViewHolder baru setiap kali RecyclerView membutuhkannya.
     * Ini akan me-layout XML untuk satu item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_recipe, parent, false)
        return ViewHolder(view)
    }

    /**
     * Mengikat (bind) data dari resep ke view di dalam ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]

        // Mengisi data ke kartu resep
        holder.tvRecipeName.text = recipe.name
        holder.tvCuisine.text = recipe.cuisine
        holder.tvRating.text = recipe.rating.toString()

        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .into(holder.ivRecipeImage)

        // Logika saat tombol "Lihat Detail" diklik
        holder.btnDetail.setOnClickListener {
            val context = holder.itemView.context

            // 1. Membuat layout custom untuk dialog dari XML
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_recipe_detail, null)

            // 2. Inisialisasi semua view yang ada di dalam layout dialog
            val dialogRecipeName: TextView = dialogView.findViewById(R.id.dialog_recipe_name)
            val dialogIngredients: TextView = dialogView.findViewById(R.id.dialog_ingredients)
            val dialogInstructions: TextView = dialogView.findViewById(R.id.dialog_instructions)
            val closeButton: ImageView = dialogView.findViewById(R.id.dialog_close_button)

            // 3. Mengisi data resep ke dalam view dialog
            dialogRecipeName.text = recipe.name

            val ingredientsText = recipe.ingredients.joinToString(separator = "\n") { "• $it" }
            dialogIngredients.text = ingredientsText

            val instructionsText = recipe.instructions.mapIndexed { index, instruction ->
                "${index + 1}. $instruction"
            }.joinToString(separator = "\n")
            dialogInstructions.text = instructionsText

            // 4. Membuat dan menampilkan AlertDialog
            val dialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .create()

            // 5. Menambahkan fungsi klik untuk tombol close di dalam dialog
            closeButton.setOnClickListener {
                dialog.dismiss() // Menutup dialog
            }

            dialog.show()
        }
    }

    /**
     * Mengembalikan jumlah total resep.
     */
    override fun getItemCount() = recipes.size

    /**
     * Fungsi untuk memperbarui semua data di adapter.
     */
    fun updateAll(newRecipes: List<DataItem>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}
