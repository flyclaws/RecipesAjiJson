package com.example.resipesajijson

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager // DIUBAH
import androidx.recyclerview.widget.RecyclerView
import com.example.resipesajijson.model.RecipeResponse
import com.example.resipesajijson.network.ApiConfig
import com.example.resipesajijson.RecipeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvRecipes: RecyclerView = findViewById(R.id.rvRecipes)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        recipeAdapter = RecipeAdapter(mutableListOf())
        // DIUBAH: Menggunakan GridLayoutManager dengan 2 kolom
        rvRecipes.layoutManager = GridLayoutManager(this, 2)
        rvRecipes.adapter = recipeAdapter

        fetchRecipes(progressBar)
    }

    private fun fetchRecipes(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        ApiConfig.getApiService().getRecipes().enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.recipes?.let { recipeAdapter.updateAll(it) }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}