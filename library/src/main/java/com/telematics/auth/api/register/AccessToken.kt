package com.telematics.auth.api.register


import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("ExpiresIn")
    val expiresIn: Long,
    @SerializedName("Token")
    val token: String
)