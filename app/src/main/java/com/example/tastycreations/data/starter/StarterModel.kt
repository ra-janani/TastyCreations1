package com.example.tastycreations.data.starter


import com.example.tastycreations.data.starter.MealModel
import com.google.gson.annotations.SerializedName

data class StarterModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)