package com.example.tastycreations.repository

import com.example.tastycreations.data.breakfast.BreakfastModel
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.seafood.MealModel
import com.example.tastycreations.data.seafood.SeafoodModel
import com.example.tastycreations.data.starter.StarterModel
import com.example.tastycreations.data.vegan.VeganModel
import com.example.tastycreations.data.vegetarian.VegetarianModel
import retrofit2.http.Query

interface Repository {


    suspend fun getStarter(): StarterModel

    suspend fun getBreakfast(): BreakfastModel

    suspend fun getVegetarian(): VegetarianModel

    suspend fun getVegan(): VeganModel

    suspend fun getSeafood(): SeafoodModel

    suspend fun getDetails(mealid:String): com.example.tastycreations.data.detail.DetailsModel
}