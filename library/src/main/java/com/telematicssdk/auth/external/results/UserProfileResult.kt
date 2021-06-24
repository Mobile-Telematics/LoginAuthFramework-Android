package com.telematicssdk.auth.external.results

import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.api.model.MaritalStatus

/**
 * Represents user profile result
 */
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