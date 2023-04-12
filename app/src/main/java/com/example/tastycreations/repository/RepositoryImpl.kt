package com.example.tastycreations.repository

import com.example.tastycreations.remote.ApiRequest
import retrofit2.http.Query
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
):Repository{


    override suspend fun getStarter() = apiRequest.getStarter()

    override suspend fun getBreakfast() = apiRequest.getBreakfast()

    override suspend fun getVegetarian() = apiRequest.getVegetarian()

    override suspend fun getVegan() = apiRequest.getVegan()

    override suspend fun getSeafood() = apiRequest.getSeafood()

    override suspend fun getDetails(mealId :String) = apiRequest.getDetail(mealId)

}