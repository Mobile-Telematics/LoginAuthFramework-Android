package com.telematics.auth.api.model.update_profile

import com.google.gson.annotations.SerializedName

internal class UserUpdateBody(
	@SerializedName("address")
	val address: String? = null,
	@SerializedName("birthday")
	val birthday: String? = null,
	@SerializedName("childrenCount")
	val childrenCount: Int? = null,
	@SerializedName("email")
	val email: String? = null,
	@SerializedName("firstName")
	val firstName: String? = null,
	@SerializedName("gender")
	val gender: Int? = null,
	@SerializedName("lastName")
	val lastName: String? = null,
	@SerializedName("maritalStatus")
	val maritalStatus: String? = null,
	@SerializedName("phone")
	val phone: String? = null,
	@SerializedName("userFields")
	val userFields: UserUpdateFields? = null
)