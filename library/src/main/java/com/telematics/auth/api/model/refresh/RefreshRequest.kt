package com.telematics.auth.api.model.refresh


import com.google.gson.annotations.SerializedName

data class RefreshRequest(
    @SerializedName("AccessToken")
    val accessToken: String,
    @SerializedName("RefreshToken")
    val refreshToken: String
)