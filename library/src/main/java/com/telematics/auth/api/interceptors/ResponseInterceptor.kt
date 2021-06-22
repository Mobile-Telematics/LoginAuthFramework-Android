package com.telematics.auth.api.interceptors

import com.telematics.auth.errors.ApiException
import com.telematics.auth.api.extensions.transform
import com.telematics.auth.api.extensions.transformErrorDetails
import okhttp3.Interceptor
import okhttp3.Response

internal class ResponseInterceptor: Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val response = chain.proceed(chain.request())

		val code = response.code()

		if (code in 400..500) throw ApiException(code)

		val body = response.body()?.string()

		if (code == 204) return transform(response, body)

		val apiResponse = transform(body)

		if (apiResponse.status == 200) return transform(response, body)

		if (apiResponse.status != 200) {
			if (apiResponse.status == 422) {
				throw ApiException(apiResponse.status, apiResponse.msg, transformErrorDetails(apiResponse.details))
			} else throw ApiException(apiResponse.status, apiResponse.msg)
		}

		return transform(response, body)
	}
}