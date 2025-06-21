package com.example.resipesajijson.network

import com.example.resipesajijson.model.RecipeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse
}