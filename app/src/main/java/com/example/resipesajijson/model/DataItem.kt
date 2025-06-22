package com.example.resipesajijson.model

import com.google.gson.annotations.SerializedName

// Disederhanakan: Tidak perlu Parcelable lagi
data class DataItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("cuisine")
    val cuisine: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("ingredients")
    val ingredients: List<String>,

    @field:SerializedName("instructions")
    val instructions: List<String>
)
