package com.telematics.auth.api.model.refresh


import com.google.gson.annotations.SerializedName

internal data class RefreshBody(
    @SerializedName("AccessToken")
    val accessToken: String,
    @SerializedName("RefreshToken")
    val refreshToken: String
)