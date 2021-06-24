package com.telematicssdk.auth.api.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.telematicssdk.auth.api.ApiResponse
import com.telematicssdk.auth.api.ErrorData
import com.telematicssdk.auth.errors.FieldErrorDetailsData
import okhttp3.Response
import okhttp3.ResponseBody


internal fun transform(body: String?): ApiResponse<Any> {
	return Gson().fromJson(body, object : TypeToken<ApiResponse<Any?>>() {}.type)
}

internal fun transform(response: Response, body: String?): Response {
	return response
		.newBuilder()
		.body(ResponseBody.create(response.body()?.contentType(), body))
		.build()
}

internal fun transformErrorDetails(response: List<ErrorData>?): List<FieldErrorDetailsData>? {
	if (response.isNullOrEmpty()) return null
	val listFieldErrorDetailsData = ArrayList<FieldErrorDetailsData>(response.size)
	response.indices.mapTo(listFieldErrorDetailsData) { convert(response[it]) }
	return listFieldErrorDetailsData
}

internal fun convert(fieldErrorResponse: ErrorData): FieldErrorDetailsData {
	val fieldErrorDetailsData = FieldErrorDetailsData()
	fieldErrorDetailsData.field = fieldErrorResponse.key
	fieldErrorDetailsData.detailMessage = fieldErrorResponse.message
	return fieldErrorDetailsData
}
