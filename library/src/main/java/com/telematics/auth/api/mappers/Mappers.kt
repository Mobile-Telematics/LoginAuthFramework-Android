package com.telematics.auth.api.mappers

import com.telematics.auth.api.model.register.Result
import com.telematics.auth.external.CreateResult
import com.telematics.auth.external.LoginResult
import com.telematics.auth.external.RefreshResult


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
