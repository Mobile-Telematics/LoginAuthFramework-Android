package com.telematics.auth.api.model


import com.google.gson.annotations.SerializedName
import com.telematics.auth.api.model.register.AccessToken

data class Result(
	@SerializedName("AccessToken")
    val accessToken: AccessToken,
	@SerializedName("DeviceToken")
    val deviceToken: String,
	@SerializedName("RefreshToken")
    val refreshToken: String
)