package com.telematics.auth.api.model.register
import com.google.gson.annotations.SerializedName

data class AuthBody(
		@SerializedName("address")
		val address: String = "",
		@SerializedName("birthday")
		val birthday: String = "",
		@SerializedName("childrenCount")
		val childrenCount: Int = 0,
		@SerializedName("email")
		val email: String = "",
		@SerializedName("firstName")
		val firstName: String = "",
		@SerializedName("gender")
		val gender: Int = 1,
		@SerializedName("lastName")
		val lastName: String = "",
		@SerializedName("maritalStatus")
		val maritalStatus: String = "",
		@SerializedName("password")
		val password: String = "",
		@SerializedName("phone")
		val phone: String = "",
		@SerializedName("userFields")
		val userFields: UserFields? = null
)