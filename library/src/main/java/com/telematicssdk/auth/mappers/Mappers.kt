package com.telematicssdk.auth.mappers

import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.api.model.MaritalStatus
import com.telematicssdk.auth.api.model.Result
import com.telematicssdk.auth.api.model.get_profile.UserInfoResponse
import com.telematicssdk.auth.external.results.CreateResult
import com.telematicssdk.auth.external.results.LoginResult
import com.telematicssdk.auth.external.results.RefreshResult
import com.telematicssdk.auth.external.results.UserProfileResult


internal fun Result.toExternalCreateResult(): CreateResult {
	return CreateResult(
		deviceToken = this.deviceToken,
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

internal fun Result.toExternalRefreshResult(): RefreshResult {
	return RefreshResult(
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

internal fun Result.toExternalLoginResult(): LoginResult {
	return LoginResult(
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

internal fun UserInfoResponse.toExternalUserProfile(): UserProfileResult {
	return UserProfileResult(
		deviceToken = this.deviceToken,
		email = this.userProfile.email,
		phone = this.userProfile.phone,
		firstName = this.userProfile.firstName,
		lastName = this.userProfile.lastName,
		birthDay = this.userProfile.birthday,
		gender = Gender.parse(this.userProfile.gender ?: ""),
		childrenCount = this.userProfile.childrenCount,
		address = this.userProfile.address,
		maritalStatus = MaritalStatus.parse(this.userProfile.maritalStatus ?: ""),
		clientId = this.userFields.find { it.containsKey("ClientId") }?.get("ClientId") as String?
	)
}
