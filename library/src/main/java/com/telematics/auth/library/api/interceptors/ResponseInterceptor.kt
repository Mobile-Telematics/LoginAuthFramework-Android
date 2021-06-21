package com.telematics.auth.library.api.interceptors

import com.telematics.auth.library.errors.ApiError
import com.telematics.auth.library.api.extensions.transform
import com.telematics.auth.library.api.extensions.transformErrorDetails
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor: Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val response = chain.proceed(chain.request())

		val code = response.code()

		if (code in 400..500) throw ApiError(code)

		val body = response.body()?.string()

		if (code == 204) return transform(response, body)

		val apiResponse = transform(body)

		if (apiResponse.status == 200) return transform(response, body)

		if (apiResponse.status != 200) {
			if (apiResponse.status == 422) {
				throw ApiError(apiResponse.status, apiResponse.msg, transformErrorDetails(apiResponse.details))
			} else throw ApiError(apiResponse.status, apiResponse.msg)
		}

		return transform(response, body)
	}
}