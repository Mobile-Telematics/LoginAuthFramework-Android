package com.telematics.auth.api

import com.telematics.auth.api.model.refresh.RefreshRequest
import com.telematics.auth.api.model.register.AuthBody
import com.telematics.auth.api.model.register.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
	@POST("v1/Registration/Create")
	fun registerUser(
		@Header("InstanceId") instanceId: String,
		@Header("InstanceKey") instanceKey: String,
		@Body body: AuthBody? = null
	): Call<ApiResponse<Result>>

	@POST("v1/Auth/RefreshToken")
	fun refreshToken(
		@Header("InstanceId") instanceId: String,
		@Header("InstanceKey") instanceKey: String,
		@Body refreshRequest: RefreshRequest
	): Call<ApiResponse<Result>>
}