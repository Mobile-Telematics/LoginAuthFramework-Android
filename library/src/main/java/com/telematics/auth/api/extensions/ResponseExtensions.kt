package com.telematics.auth.api.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.telematics.auth.api.ApiResponse
import com.telematics.auth.api.ErrorData
import com.telematics.auth.errors.FieldDetailsDataError
import okhttp3.Response
import okhttp3.ResponseBody


fun transform(body: String?): ApiResponse<Any> {
	return Gson().fromJson(body, object : TypeToken<ApiResponse<Any?>>() {}.type)
}

fun transform(response: Response, body: String?): Response {
	return response
		.newBuilder()
		.body(ResponseBody.create(response.body()?.contentType(), body))
		.build()
}

fun transformErrorDetails(response: List<ErrorData>?): List<FieldDetailsDataError>? {
	if (response.isNullOrEmpty()) return null
	val listFieldErrorDetailsData = ArrayList<FieldDetailsDataError>(response.size)
	response.indices.mapTo(listFieldErrorDetailsData) { convert(response[it]) }
	return listFieldErrorDetailsData
}

private fun convert(fieldErrorResponse: ErrorData): FieldDetailsDataError {
	val fieldErrorDetailsData = FieldDetailsDataError()
	fieldErrorDetailsData.field = fieldErrorResponse.key
	fieldErrorDetailsData.detailMessage = fieldErrorResponse.message
	return fieldErrorDetailsData
}
