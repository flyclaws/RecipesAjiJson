package com.example.resipesajijson

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resipesajijson.model.RecipeResponse
import com.example.resipesajijson.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Views
        val rvRecipes: RecyclerView = findViewById(R.id.rvRecipes)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        // Setup RecyclerView & Adapter
        val recipeAdapter = RecipeAdapter(mutableListOf())
        rvRecipes.layoutManager = LinearLayoutManager(this)
        rvRecipes.adapter = recipeAdapter

        // Mengambil data dari API
        progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getRecipes()

        client.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    response.body()?.recipes?.let {
                        recipeAdapter.updateAll(it)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}