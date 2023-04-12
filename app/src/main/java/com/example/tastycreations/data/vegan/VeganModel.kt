package com.example.tastycreations.data.vegan


import com.google.gson.annotations.SerializedName

data class VeganModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)