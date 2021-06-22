package com.telematics.auth.mappers

import com.telematics.auth.api.model.Gender
import com.telematics.auth.api.model.MaritalStatus
import com.telematics.auth.api.model.Result
import com.telematics.auth.api.model.get_profile.UserInfoResponse
import com.telematics.auth.external.results.CreateResult
import com.telematics.auth.external.results.LoginResult
import com.telematics.auth.external.results.RefreshResult
import com.telematics.auth.external.results.UserProfileResult


fun Result.toExternalCreateResult(): CreateResult {
	return CreateResult(
		deviceToken = this.deviceToken,
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

fun Result.toExternalRefreshResult(): RefreshResult {
	return RefreshResult(
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

fun Result.toExternalLoginResult(): LoginResult {
	return LoginResult(
		refreshToken = this.refreshToken,
		accessToken = this.accessToken.token
	)
}

fun UserInfoResponse.toExternalUserProfile(): UserProfileResult {
	return UserProfileResult(
		deviceToken = this.deviceToken,
		email = this.userProfile.email,
		phone = this.userProfile.phone,
		firstName = this.userProfile.firstName,
		lastName = this.userProfile.lastName,
		birthDay = this.userProfile.birthday,
		gender = Gender.parse(this.userProfile.gender?:""),
		childrenCount = this.userProfile.childrenCount,
		address = this.userProfile.address,
		maritalStatus = MaritalStatus.parse(this.userProfile.maritalStatus?:""),
		clientId = this.userFields[1]["ClientId"] as String?
	)
}
