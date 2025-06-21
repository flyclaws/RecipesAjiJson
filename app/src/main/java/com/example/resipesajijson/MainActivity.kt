package com.example.resipesajijson

import android.os.Bundle
import android.view.WindowInsetsAnimation
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resipesajijson.model.RecipeResponse
import com.example.resipesajijson.network.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainActivity {

    private lateinit var rvRecipes: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvRecipes = findViewById(R.id.rvRecipes)
        progressBar = findViewById(R.id.progressBar)

        setupRecyclerView()
        fetchRecipes()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(mutableListOf())
        rvRecipes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recipeAdapter
        }
    }

    /**
     * Mengambil data resep dari API menggunakan metode enqueue (Callback)
     * sesuai dengan template yang diberikan.
     */
    private suspend fun fetchRecipes() {
        showLoading(true)
        val client = ApiConfig.getApiService().getRecipes()

        client.enqueue(object : WindowInsetsAnimation.Callback<RecipeResponse> {
            // Metode ini akan dipanggil jika ada respons dari server (baik sukses maupun gagal)
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val recipeList = response.body()?.recipes
                    if (recipeList != null) {
                        // Menggunakan updateAll agar lebih efisien daripada menambah satu per satu
                        recipeAdapter.updateAll(recipeList)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal memuat data: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            // Metode ini akan dipanggil jika terjadi kegagalan koneksi ke server
            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                showLoading(false)
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                t.printStackTrace()
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}