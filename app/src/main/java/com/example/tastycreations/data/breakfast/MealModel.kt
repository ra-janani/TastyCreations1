package com.example.tastycreations.data.breakfast


import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("idMeal")
    val idMeal: String = "",
    @SerializedName("strMeal")
    val strMeal: String? = "",
    @SerializedName("strMealThumb")
    val strMealThumb: String? = ""
):java.io.Serializable