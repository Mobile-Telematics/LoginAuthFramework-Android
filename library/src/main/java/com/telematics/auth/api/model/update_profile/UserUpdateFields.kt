package com.telematics.auth.api.model.update_profile

import com.google.gson.annotations.SerializedName

internal class UserUpdateFields(
	@SerializedName("ClientId")
	val clientId: String? = null,
	@SerializedName("EnableLogging")
	val enableLogging: Boolean? = null,
	@SerializedName("EnableRealTimeLocation")
	val enableRealTimeLocation: Boolean? = null,
	@SerializedName("EnableTracking")
	val enableTracking: Boolean? = null,
	@SerializedName("Enabled")
	val enabled: Boolean? = null
)