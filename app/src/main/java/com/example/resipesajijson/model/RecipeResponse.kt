package com.example.resipesajijson.model

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @field:SerializedName("recipes")
    val recipes: List<DataItem>,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("skip")
    val skip: Int,

    @field:SerializedName("limit")
    val limit: Int
)
