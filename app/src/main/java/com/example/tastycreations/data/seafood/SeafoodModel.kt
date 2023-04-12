package com.example.tastycreations.data.seafood


import com.google.gson.annotations.SerializedName

data class SeafoodModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)