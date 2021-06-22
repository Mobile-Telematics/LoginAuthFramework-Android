package com.telematics.auth.api

import com.telematics.auth.api.model.login.LoginBody
import com.telematics.auth.api.model.refresh.RefreshBody
import com.telematics.auth.api.model.register.AuthBody
import com.telematics.auth.api.model.Result
import com.telematics.auth.api.model.get_profile.UserInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserServiceApi {
	@POST("v1/Registration/Create")
	fun registerUser(
		@Header("InstanceId") instanceId: String,
		@Header("InstanceKey") instanceKey: String,
		@Body body: AuthBody
	): Call<ApiResponse<Result>>

	@POST("v1/Auth/Login")
	fun login(
		@Header("InstanceId") instanceId: String,
		@Body loginBody: LoginBody
	): Call<ApiResponse<Result>>

	@POST("v1/Auth/RefreshToken")
	fun refreshToken(
		@Header("InstanceId") instanceId: String,
		@Header("InstanceKey") instanceKey: String,
		@Body refreshBody: RefreshBody
	): Call<ApiResponse<Result>>

	@GET("v1/Management/users")
	fun getProfile(
		@Header("InstanceId") instanceId: String,
		@Header("InstanceKey") instanceKey: String,
		@Header("Authorization") accessToken: String
	): Call<ApiResponse<UserInfoResponse>>
}