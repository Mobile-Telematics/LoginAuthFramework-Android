package com.telematics.auth.api.model.login


import com.google.gson.annotations.SerializedName

internal data class LoginBody(
	@SerializedName("loginFields")
	val loginFields: LoginFields,
	@SerializedName("password")
	val password: String
)