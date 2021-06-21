package com.telematics.auth.library

import com.telematics.auth.library.api.Api
import com.telematics.auth.library.api.ApiResponse
import com.telematics.auth.library.api.interceptors.ResponseInterceptor
import com.telematics.auth.library.api.mappers.toExternalCreateResult
import com.telematics.auth.library.api.mappers.toExternalRefreshResult
import com.telematics.auth.library.api.refresh.RefreshRequest
import com.telematics.auth.library.api.register.Result
import com.telematics.auth.library.errors.EmptyResultException
import com.telematics.auth.library.external.CreateResult
import com.telematics.auth.library.external.RefreshResult
import com.telematics.auth.library.external.Task
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

	fun createDeviceToken(instanceId: String, instanceKey: String): Task<CreateResult> {
		val task = Task<CreateResult>()
		api.registerUser(instanceId, instanceKey).enqueue(
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
		val request = RefreshRequest(accessToken, refreshToken)
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

}