package com.example.ch8.data.remote.service

import com.example.ch8.data.remote.response.MenuItemRespone
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SpoonacularService {
    /**
     * Find movies using over 30 filters and sort options.
     */
    @GET("food/menuItems/search")
    suspend fun fetchMovies(
        // MenuItem
        @Query("apiKey") apiKey: String = "bf0138bf61ce4a1a9c3bfdc745cf9559",
        @Query("number") number: Int = 100,
        @Query("query") query: String = "pie",
    ): MenuItemRespone

    @POST("auth/editProfile")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body editProfileBody: EditProfileBody,
    ): EditProfileBody
}

data class EditProfileBody(
    val name: String,
    val image: String,
)
