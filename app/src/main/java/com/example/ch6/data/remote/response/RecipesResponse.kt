package com.example.ch6.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<ResultX>?,
    @SerializedName("totalResults")
    val totalResults: Int?
)