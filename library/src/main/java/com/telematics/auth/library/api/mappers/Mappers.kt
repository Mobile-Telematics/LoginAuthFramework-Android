package com.telematics.auth.library.api.mappers

import com.telematics.auth.library.api.register.Result
import com.telematics.auth.library.external.CreateResult
import com.telematics.auth.library.external.RefreshResult


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
