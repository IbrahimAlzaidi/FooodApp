package com.example.fooodapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.example.fooodapp.data.Repository
import com.example.fooodapp.data.database.RecipesEntity
import com.example.fooodapp.models.FoodRecipe
import com.example.fooodapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {


    // ********** Room DATABASE ********** //
    var readRecipes: LiveData<List<RecipesEntity>> = repository.local.readFromDatabase().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity)= viewModelScope.launch(Dispatchers.IO) {
        repository.local.insertRecipes(recipesEntity)
    }

    // ********** Retrofit ********** //
    var recipeResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    var searchRecipeResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    fun searchRecipes(searchQuery: Map<String, String>) = viewModelScope.launch {
        searchRecipesSafeCall(searchQuery)
    }


    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipeResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipeResponse.value!!.data
                if (foodRecipe != null){
                    offlineCacheRecipes(foodRecipe)
                }
            } catch (e: Exception) {
                recipeResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            recipeResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private suspend fun searchRecipesSafeCall(searchQuery: Map<String, String>) {
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.searchRecipes(searchQuery)
                searchRecipeResponse.value = handleFoodRecipesResponse(response)

            } catch (e: Exception) {
                searchRecipeResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            searchRecipeResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        when {
            (response.message().toString().contains("timeout")) -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetWork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetWork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}