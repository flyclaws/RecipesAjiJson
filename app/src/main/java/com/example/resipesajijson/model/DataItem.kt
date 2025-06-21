package com.example.resipesajijson.model

import com.google.gson.annotations.SerializedName

data class DataItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("ingredients")
    val ingredients: List<String>,

    @field:SerializedName("instructions")
    val instructions: List<String>,

    @field:SerializedName("prepTimeMinutes")
    val prepTimeMinutes: Int,

    @field:SerializedName("cookTimeMinutes")
    val cookTimeMinutes: Int,

    @field:SerializedName("servings")
    val servings: Int,

    @field:SerializedName("difficulty")
    val difficulty: String,

    @field:SerializedName("cuisine")
    val cuisine: String,

    @field:SerializedName("caloriesPerServing")
    val caloriesPerServing: Int,

    @field:SerializedName("tags")
    val tags: List<String>,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("reviewCount")
    val reviewCount: Int,

    @field:SerializedName("mealType")
    val mealType: List<String>
)
