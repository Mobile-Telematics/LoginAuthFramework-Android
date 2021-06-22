package com.telematics.auth.api.model.get_profile


import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
	@SerializedName("DeviceToken")
	val deviceToken: String,
	@SerializedName("Status")
	val status: String,
	@SerializedName("UserProfile")
	val userProfile: UserProfile,
	@SerializedName("UserFields")
	val userFields: List<Map<String, Any>>
)