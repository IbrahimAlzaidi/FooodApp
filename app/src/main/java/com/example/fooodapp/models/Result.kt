package com.example.fooodapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Result(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("cheap")
    val cheap: Boolean? = null,
    @SerializedName("dairyFree")
    val dairyFree: Boolean? = null,
    @SerializedName("extendedIngredients")
    val extendedIngredients: @RawValue List<ExtendedIngredient>? = null,
    @SerializedName("glutenFree")
    val glutenFree: Boolean? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("license")
    val license: String? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerializedName("sourceName")
    val sourceName: String? = null,
    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vegan")
    val vegan: Boolean? = null,
    @SerializedName("vegetarian")
    val vegetarian: Boolean? = null,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean? = null
) : Parcelable