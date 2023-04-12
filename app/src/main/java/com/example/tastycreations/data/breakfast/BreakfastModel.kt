package com.example.tastycreations.data.breakfast


import com.google.gson.annotations.SerializedName

data class BreakfastModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)