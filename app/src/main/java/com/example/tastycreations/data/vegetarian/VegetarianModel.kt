package com.example.tastycreations.data.vegetarian


import com.google.gson.annotations.SerializedName

data class VegetarianModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)