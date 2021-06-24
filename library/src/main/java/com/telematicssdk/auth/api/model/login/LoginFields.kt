package com.telematicssdk.auth.api.model.login

import com.google.gson.annotations.SerializedName

internal data class LoginFields(
	@SerializedName("Phone")
	val phone: String? = null,
	@SerializedName("Email")
	val email: String? = null,
	@SerializedName("DeviceToken")
	val deviceToken: String? = null
)