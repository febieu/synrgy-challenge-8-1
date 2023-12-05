package com.example.ch7.data.remote.response

import com.google.gson.annotations.SerializedName

data class MenuItemRespone(
    @SerializedName("menuItems")
    val menuItems: List<MenuItem>?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("processingTimeMs")
    val processingTimeMs: Int?,
    @SerializedName("totalMenuItems")
    val totalMenuItems: Int?,
    @SerializedName("type")
    val type: String?
)

