package com.example.tastycreations.data.detail


import com.google.gson.annotations.SerializedName

data class DetailsModel(
    @SerializedName("meals")
    val meals: List<MealModel?>? = listOf()
)