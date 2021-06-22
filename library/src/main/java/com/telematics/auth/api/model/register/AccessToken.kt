package com.telematics.auth.api.model.register


import com.google.gson.annotations.SerializedName

internal data class AccessToken(
    @SerializedName("ExpiresIn")
    val expiresIn: Long,
    @SerializedName("Token")
    val token: String
)