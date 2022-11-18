package com.example.fooodapp.data

import com.example.fooodapp.data.network.FoodRecipesApi
import com.example.fooodapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries:Map<String,String>): Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }
}