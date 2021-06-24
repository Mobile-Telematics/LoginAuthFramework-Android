package com.telematicssdk.auth.api.model


import com.google.gson.annotations.SerializedName
import com.telematicssdk.auth.api.model.register.AccessToken

internal data class Result(
	@SerializedName("AccessToken")
    val accessToken: AccessToken,
	@SerializedName("DeviceToken")
    val deviceToken: String,
	@SerializedName("RefreshToken")
    val refreshToken: String
)