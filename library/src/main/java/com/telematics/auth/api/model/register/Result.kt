package com.telematics.auth.api.model.register


import com.google.gson.annotations.SerializedName

data class Result(
	@SerializedName("AccessToken")
    val accessToken: AccessToken,
	@SerializedName("DeviceToken")
    val deviceToken: String,
	@SerializedName("RefreshToken")
    val refreshToken: String
)