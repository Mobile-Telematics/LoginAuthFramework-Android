package com.telematics.auth

import com.telematics.auth.api.model.Gender
import com.telematics.auth.api.model.MaritalStatus
import com.telematics.auth.external.results.CreateResult
import com.telematics.auth.external.results.LoginResult
import com.telematics.auth.external.results.RefreshResult
import com.telematics.auth.external.Task
import com.telematics.auth.external.results.UserProfileResult
import retrofit2.converter.gson.GsonConverterFactory

object TelematicsAuth {
	private val delegate: AuthDelegate by lazy {
		AuthDelegate(BuildConfig.USER_SERVICE_URL, GsonConverterFactory.create())
	}

	@JvmStatic
	fun createDeviceToken(instanceId: String,
						  instanceKey: String,
						  email: String? = null,
						  phone: String? = null,
						  clientId: String? = null,
						  firstName: String? = null,
						  lastName: String? = null,
						  birthDay: String? = null,
						  maritalStatus: MaritalStatus? = null,
						  childrenCount: Int? = null,
						  address: String? = null,
						  gender: Gender? = null
	): Task<CreateResult> {
		return delegate.createDeviceToken(instanceId, instanceKey, email,
			phone,
			clientId,
			firstName,
			lastName,
			birthDay,
			maritalStatus,
			childrenCount,
			address,
			gender)
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

	@JvmStatic
	fun login(
		instanceId: String,
		instanceKey: String,
		deviceToken: String
	): Task<LoginResult> {
		return delegate.login(instanceId, instanceKey, deviceToken)
	}

	@JvmStatic
	fun getUserProfile(
		instanceId: String,
		instanceKey: String,
		accessToken: String
	): Task<UserProfileResult> {
		return delegate.getUserProfile(instanceId,
			instanceKey,
			accessToken)
	}

	@JvmStatic
	fun updateUserProfile(
		instanceId: String,
		instanceKey: String,
		deviceToken: String,
		accessToken: String,
		email: String? = null,
		phone: String? = null,
		clientId: String? = null,
		firstName: String? = null,
		lastName: String? = null,
		birthDay: String? = null,
		maritalStatus: MaritalStatus? = null,
		childrenCount: Int? = null,
		address: String? = null,
		gender: Gender? = null
	): Task<Unit> {
		return delegate.updateUserProfile(
			instanceId,
			instanceKey,
			accessToken,
			deviceToken,
			email,
			phone,
			clientId,
			firstName,
			lastName,
			birthDay,
			maritalStatus,
			childrenCount,
			address,
			gender
		)
	}
}