package com.telematics.auth.external.results

import com.telematics.auth.api.model.Gender
import com.telematics.auth.api.model.MaritalStatus

data class UserProfileResult(
	val deviceToken: String,
	val email: String? = null,
	val phone: String? = null,
	val firstName: String? = null,
	val lastName: String? = null,
	val clientId: String? = null,
	val birthDay: String? = null,
	val gender: Gender? = null,
	val childrenCount: Int? = null,
	val address: String? = null,
	val maritalStatus: MaritalStatus? = null
)