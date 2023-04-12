package com.example.tastycreations.remote

import android.telecom.Call.Details
import com.example.tastycreations.data.breakfast.BreakfastModel
import com.example.tastycreations.data.detail.DetailsModel
import com.example.tastycreations.data.detail.MealModel
import com.example.tastycreations.data.seafood.SeafoodModel
import com.example.tastycreations.data.starter.StarterModel
import com.example.tastycreations.data.vegan.VeganModel
import com.example.tastycreations.data.vegetarian.VegetarianModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {


    @GET(ApiDetails.END_PT1)
    suspend  fun getStarter():StarterModel

    @GET(ApiDetails.END_PT2)
    suspend  fun getBreakfast():BreakfastModel

    @GET(ApiDetails.END_PT3)
    suspend  fun getVegetarian(): VegetarianModel

    @GET(ApiDetails.END_PT4)
    suspend  fun getVegan(): VeganModel

    @GET(ApiDetails.END_PT5)
    suspend fun getSeafood() : SeafoodModel

    @GET(ApiDetails.END_PT6)
    suspend fun getDetail(@Query("i") mealId :String) : DetailsModel


}