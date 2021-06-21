package com.telematics.auth.library

import com.telematics.auth.library.external.CreateResult
import com.telematics.auth.library.external.RefreshResult
import com.telematics.auth.library.external.Task
import retrofit2.converter.gson.GsonConverterFactory

object TelematicsAuth {
	private val delegate: AuthDelegate by lazy {
		AuthDelegate(BuildConfig.USER_SERVICE_URL, GsonConverterFactory.create())
	}

	@JvmStatic
	fun createDeviceToken(instanceId: String, instanceKey: String): Task<CreateResult> {
		return delegate.createDeviceToken(instanceId, instanceKey)
	}

	@JvmStatic
	fun refreshToken(
		instanceId: String,
		instanceKey: String,
		accessToken: String,
		refreshToken: String
	): Task<RefreshResult> {
		return delegate.refreshToken(instanceId, instanceKey, accessToken, refreshToken)
	}
}