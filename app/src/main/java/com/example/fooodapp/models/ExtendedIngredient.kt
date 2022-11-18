package com.example.fooodapp.models


import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("consistency")
    val consistency: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nameClean")
    val nameClean: String,
    @SerializedName("unit")
    val unit: String
)