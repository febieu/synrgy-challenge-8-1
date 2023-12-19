package com.example.ch8.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("title")
    val title: String?,
)
