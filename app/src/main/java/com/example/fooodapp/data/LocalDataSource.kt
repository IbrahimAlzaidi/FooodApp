package com.example.fooodapp.data

import com.example.fooodapp.data.database.RecipesDao
import com.example.fooodapp.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readFromDatabase(): Flow<List<RecipesEntity>> =
        recipesDao.readRecipes()


    suspend fun insertRecipes(recipesEntity: RecipesEntity) =
        recipesDao.insertRecipes(recipesEntity)

}