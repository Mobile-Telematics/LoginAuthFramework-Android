package com.telematics.auth

import com.telematics.auth.api.Api
import com.telematics.auth.api.ApiResponse
import com.telematics.auth.api.interceptors.ResponseInterceptor
import com.telematics.auth.api.mappers.toExternalCreateResult
import com.telematics.auth.api.mappers.toExternalLoginResult
import com.telematics.auth.api.mappers.toExternalRefreshResult
import com.telematics.auth.api.model.Gender
import com.telematics.auth.api.model.MaritalStatus
import com.telematics.auth.api.model.login.LoginBody
import com.telematics.auth.api.model.login.LoginFields
import com.telematics.auth.api.model.refresh.RefreshBody
import com.telematics.auth.api.model.register.AuthBody
import com.telematics.auth.api.model.register.Result
import com.telematics.auth.api.model.register.UserFields
import com.telematics.auth.errors.EmptyResultException
import com.telematics.auth.external.results.CreateResult
import com.telematics.auth.external.results.LoginResult
import com.telematics.auth.external.results.RefreshResult
import com.telematics.auth.external.Task
import okhttp3.OkHttpClient
import retrofit2.*

class AuthDelegate(
	baseUrl: String,
	converterFactory: Converter.Factory
) {

	private val api: Api

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
		api = retrofit.create(Api::class.java)
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
			email = email?:"",
			phone = phone?:"",
			firstName = firstName?:"",
			lastName = lastName?:"",
			birthday = birthDay?:"",
			maritalStatus = maritalStatus?.toString()?:"",
			childrenCount = childrenCount?:0,
			address = address?:"",
			gender = gender?.ordinal?:0,
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

}