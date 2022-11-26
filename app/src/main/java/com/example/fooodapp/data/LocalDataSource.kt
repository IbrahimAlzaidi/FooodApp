package com.example.fooodapp.data

import com.example.fooodapp.data.database.RecipesDao
import com.example.fooodapp.data.database.entities.FavoritesEntity
import com.example.fooodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readRecipes(): Flow<List<RecipesEntity>> =
        recipesDao.readRecipes()

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> = recipesDao.readFavoriteRecipes()

    suspend fun insertRecipes(recipesEntity: RecipesEntity) =
        recipesDao.insertRecipes(recipesEntity)

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) =
        recipesDao.insertFavoriteRecipe(favoritesEntity)

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) =
        recipesDao.deleteFavoriteRecipes(favoritesEntity)

    suspend fun deleteAllFavoriteRecipes() = recipesDao.deleteAllFavoriteRecipes()
}