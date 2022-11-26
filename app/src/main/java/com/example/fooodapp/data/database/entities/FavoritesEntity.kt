package com.example.fooodapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fooodapp.util.Constants.Companion.FAVORITE_RECIPES_TABLE
import com.example.fooodapp.models.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)