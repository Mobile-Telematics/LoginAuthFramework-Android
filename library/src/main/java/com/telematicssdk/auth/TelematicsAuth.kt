package com.telematicssdk.auth

import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.api.model.MaritalStatus
import com.telematicssdk.auth.external.results.CreateResult
import com.telematicssdk.auth.external.results.LoginResult
import com.telematicssdk.auth.external.results.RefreshResult
import com.telematicssdk.auth.external.Task
import com.telematicssdk.auth.external.results.UserProfileResult
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Entry point of Telematics authorization library.
 *
 * Compatible with both Kotlin and Java languages
 */
object TelematicsAuth {
	private val delegate: AuthDelegate by lazy {
		AuthDelegate(BuildConfig.USER_SERVICE_URL, GsonConverterFactory.create())
	}

	/**
	 * Creates new user with specified [instanceId], [instanceKey] and optional parameters
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param email (optional)
	 * @param phone (optional)
	 * @param clientId (optional)
	 * @param firstName (optional)
	 * @param lastName (optional)
	 * @param birthDay (optional) - date-time string in yyyy-MM-dd'T'HH:mm:ss format
	 * @param maritalStatus (optional) - [MaritalStatus] enum entry
	 * @param childrenCount (optional)
	 * @param address (optional) - address string
	 * @param gender (optional) - [Gender] enum entry
	 * @return [Task] parametrised with [CreateResult]
	 */
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
		return delegate.createDeviceToken(
			instanceId,
			instanceKey,
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

	/**
	 * Refreshes [accessToken] when it expires.
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param accessToken (required)
	 * @param refreshToken (required)
	 * @return [Task] parametrised with [RefreshResult]
	 */
	@JvmStatic
	fun refreshToken(
		instanceId: String,
		instanceKey: String,
		accessToken: String,
		refreshToken: String
	): Task<RefreshResult> {
		return delegate.refreshToken(
			instanceId,
			instanceKey,
			accessToken,
			refreshToken
		)
	}

	/**
	 * Refreshes [accessToken] when it expires or authorises user using [instanceId], [instanceKey] and [deviceToken] when [refreshToken] expires.
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param deviceToken (required)
	 * @param accessToken (required)
	 * @param refreshToken (required)
	 * @return [Task] parametrised with [RefreshResult]
	 */
	@JvmStatic
	fun refreshTokenOrLogin(
		instanceId: String,
		instanceKey: String,
		deviceToken: String,
		accessToken: String,
		refreshToken: String
	): Task<RefreshResult> {
		return delegate.refreshTokenOrLogin(
			instanceId,
			instanceKey,
			deviceToken,
			accessToken,
			refreshToken
		)
	}

	/**
	 * Authorises user using [instanceId], [instanceKey] and [deviceToken].
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param deviceToken (required)
	 * @return [Task] parametrised with [RefreshResult]
	 */
	@JvmStatic
	fun login(
		instanceId: String,
		instanceKey: String,
		deviceToken: String
	): Task<LoginResult> {
		return delegate.login(
			instanceId,
			instanceKey,
			deviceToken
		)
	}

	/**
	 * Returns users profile information.
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param accessToken (required)
	 * @return [Task] parametrised with [UserProfileResult]
	 */
	@JvmStatic
	fun getUserProfile(
		instanceId: String,
		instanceKey: String,
		accessToken: String
	): Task<UserProfileResult> {
		return delegate.getUserProfile(
			instanceId,
			instanceKey,
			accessToken
		)
	}

	/**
	 * Updates user profile information
	 * @param instanceId (required)
	 * @param instanceKey (required)
	 * @param deviceToken (required)
	 * @param accessToken (required)
	 * @param email (optional)
	 * @param phone (optional)
	 * @param clientId (optional)
	 * @param firstName (optional)
	 * @param lastName (optional)
	 * @param birthDay (optional) - date-time string in yyyy-MM-dd'T'HH:mm:ss format
	 * @param maritalStatus (optional) - [MaritalStatus] enum entry
	 * @param childrenCount (optional)
	 * @param address (optional) - address string
	 * @param gender (optional) - [Gender] enum entry
	 * @return [Task] parametrised with [CreateResult]
	 */
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