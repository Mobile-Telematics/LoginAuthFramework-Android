package com.telematics.auth

import com.telematics.auth.api.ApiResponse
import com.telematics.auth.api.UserServiceApi
import com.telematics.auth.api.interceptors.ResponseInterceptor
import com.telematics.auth.api.model.Gender
import com.telematics.auth.api.model.MaritalStatus
import com.telematics.auth.api.model.Result
import com.telematics.auth.api.model.get_profile.UserInfoResponse
import com.telematics.auth.api.model.login.LoginBody
import com.telematics.auth.api.model.login.LoginFields
import com.telematics.auth.api.model.refresh.RefreshBody
import com.telematics.auth.api.model.register.AuthBody
import com.telematics.auth.api.model.register.UserFields
import com.telematics.auth.api.model.update_profile.UserUpdateBody
import com.telematics.auth.api.model.update_profile.UserUpdateFields
import com.telematics.auth.errors.EmptyResultException
import com.telematics.auth.external.Task
import com.telematics.auth.external.results.CreateResult
import com.telematics.auth.external.results.LoginResult
import com.telematics.auth.external.results.RefreshResult
import com.telematics.auth.external.results.UserProfileResult
import com.telematics.auth.mappers.toExternalCreateResult
import com.telematics.auth.mappers.toExternalLoginResult
import com.telematics.auth.mappers.toExternalRefreshResult
import com.telematics.auth.mappers.toExternalUserProfile
import okhttp3.OkHttpClient
import retrofit2.*

internal class AuthDelegate(
	baseUrl: String,
	converterFactory: Converter.Factory
) {

	private val api: UserServiceApi

	init {
		val client = OkHttpClient
			.Builder()
			.addInterceptor(ResponseInterceptor())
			.build()
		val retrofit = Retrofit
			.Builder()
			.client(client)
			.baseUrl(baseUrl)
			.addConverterFactory(converterFactory)
			.build()
		api = retrofit.create(UserServiceApi::class.java)
	}

	fun createDeviceToken(
		instanceId: String,
		instanceKey: String,
		email: String?,
		phone: String?,
		clientId: String?,
		firstName: String?,
		lastName: String?,
		birthDay: String?,
		maritalStatus: MaritalStatus?,
		childrenCount: Int?,
		address: String?,
		gender: Gender?
	): Task<CreateResult> {
		val task = Task<CreateResult>()
		val body = AuthBody(
			email = email,
			phone = phone,
			firstName = firstName,
			lastName = lastName,
			birthday = birthDay,
			maritalStatus = maritalStatus?.code.toString(),
			childrenCount = childrenCount,
			address = address,
			gender = gender?.name,
			userFields = UserFields(clientId = clientId)
		)
		api.registerUser(instanceId, instanceKey, body).enqueue(
			object : Callback<ApiResponse<Result>> {
				override fun onResponse(
					call: Call<ApiResponse<Result>>,
					response: Response<ApiResponse<Result>>
				) {
					response.body()?.result?.let {
						task.success(it.toExternalCreateResult())
					} ?: task.error(EmptyResultException())
				}

				override fun onFailure(call: Call<ApiResponse<Result>>, t: Throwable) {
					task.error(t)
				}
			}
		)
		return task
	}

	fun refreshToken(
		instanceId: String,
		instanceKey: String,
		accessToken: String,
		refreshToken: String
	): Task<RefreshResult> {
		val task = Task<RefreshResult>()
		val request = RefreshBody(accessToken, refreshToken)
		api.refreshToken(instanceId, instanceKey, request).enqueue(
			object : Callback<ApiResponse<Result>> {
				override fun onResponse(
					call: Call<ApiResponse<Result>>,
					response: Response<ApiResponse<Result>>
				) {
					response.body()?.result?.let {
						task.success(it.toExternalRefreshResult())
					} ?: task.error(EmptyResultException())
				}

				override fun onFailure(call: Call<ApiResponse<Result>>, t: Throwable) {
					task.error(t)
				}
			}
		)
		return task
	}

	fun login(instanceId: String, instanceKey: String, deviceToken: String): Task<LoginResult> {
		val task = Task<LoginResult>()

		val body = LoginBody(
			password = instanceKey,
			loginFields = LoginFields(deviceToken = deviceToken)
		)
		api.login(instanceId, body).enqueue(
			object : Callback<ApiResponse<Result>> {
				override fun onResponse(
					call: Call<ApiResponse<Result>>,
					response: Response<ApiResponse<Result>>
				) {
					response.body()?.result?.let {
						task.success(it.toExternalLoginResult())
					} ?: task.error(EmptyResultException())
				}

				override fun onFailure(call: Call<ApiResponse<Result>>, t: Throwable) {
					task.error(t)
				}
			}
		)
		return task
	}

	fun getUserProfile(
		instanceId: String,
		instanceKey: String,
		accessToken: String
	): Task<UserProfileResult> {
		val task = Task<UserProfileResult>()

		val authHeader = "Bearer $accessToken"
		api.getProfile(instanceId, instanceKey, authHeader).enqueue(
			object : Callback<ApiResponse<UserInfoResponse>> {
				override fun onResponse(
					call: Call<ApiResponse<UserInfoResponse>>,
					response: Response<ApiResponse<UserInfoResponse>>
				) {
					response.body()?.result?.let {
						task.success(it.toExternalUserProfile())
					} ?: task.error(EmptyResultException())
				}

				override fun onFailure(call: Call<ApiResponse<UserInfoResponse>>, t: Throwable) {
					task.error(t)
				}
			}
		)
		return task
	}

	fun updateUserProfile(
		instanceId: String,
		instanceKey: String,
		accessToken: String,
		deviceToken: String,
		email: String?,
		phone: String?,
		clientId: String?,
		firstName: String?,
		lastName: String?,
		birthDay: String?,
		maritalStatus: MaritalStatus?,
		childrenCount: Int?,
		address: String?,
		gender: Gender?
	): Task<Unit> {
		val task = Task<Unit>()

		val body = UserUpdateBody(
			address = address,
			birthday = birthDay,
			childrenCount = childrenCount,
			email = email,
			firstName = firstName,
			gender = gender?.name,
			lastName = lastName,
			maritalStatus = maritalStatus?.code.toString(),
			phone = phone,
			userFields = UserUpdateFields(clientId = clientId)
		)

		val authHeader = "Bearer $accessToken"
		api.updateUser(
			instanceId, instanceKey, deviceToken, authHeader, body
		).enqueue(
			object : Callback<ApiResponse<Any?>> {
				override fun onResponse(
					call: Call<ApiResponse<Any?>>,
					response: Response<ApiResponse<Any?>>
				) {
					task.success(Unit)
				}

				override fun onFailure(call: Call<ApiResponse<Any?>>, t: Throwable) {
					task.error(t)
				}
			}
		)
		return task
	}

}