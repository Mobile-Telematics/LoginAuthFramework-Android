package com.telematics.auth.mappers

import com.telematics.auth.api.model.register.Result
import com.telematics.auth.external.results.CreateResult
import com.telematics.auth.external.results.LoginResult
import com.telematics.auth.external.results.RefreshResult


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
