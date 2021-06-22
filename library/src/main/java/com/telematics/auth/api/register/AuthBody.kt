package com.telematics.auth.api.register
import com.google.gson.annotations.SerializedName

data class AuthBody(
		@SerializedName("address")
		val address: String = "",
		@SerializedName("birthday")
		val birthday: String = "",
		@SerializedName("childrenCount")
		val childrenCount: Int = 0,
		@SerializedName("city")
		val city: String = "",
		@SerializedName("country")
		val country: String = "",
		@SerializedName("createAccessToken")
		val createAccessToken: Boolean = true,
		@SerializedName("district")
		val district: String = "",
		@SerializedName("email")
		val email: String = "",
		@SerializedName("firstName")
		val firstName: String = "",
		@SerializedName("gender")
		val gender: Int = 0,
		@SerializedName("generatePassword")
		val generatePassword: Boolean = true,
		@SerializedName("imageUrl")
		val imageUrl: String = "",
		@SerializedName("lastName")
		val lastName: String = "",
		@SerializedName("maritalStatus")
		val maritalStatus: String = "",
		@SerializedName("nickname")
		val nickname: String = "",
		@SerializedName("password")
		val password: String = "",
		@SerializedName("phone")
		val phone: String = "",
		@SerializedName("userFields")
		val userFields: UserFields? = null
)