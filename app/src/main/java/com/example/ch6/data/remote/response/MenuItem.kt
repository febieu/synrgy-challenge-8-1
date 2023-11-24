package com.example.ch6.data.remote.response

import com.example.ch6.domain.Movie
import com.google.gson.annotations.SerializedName

data class MenuItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("restaurantChain")
    val restaurantChain: String?,
    @SerializedName("servings")
    val servings: Servings?,
    @SerializedName("title")
    val title: String?
)
fun MenuItem.toMovie(): Movie {
    return Movie(
        title = title.orEmpty(),
        image = image.orEmpty(),
        restaurantChain = restaurantChain.orEmpty(),
        id = id ?: -1,
    )
}