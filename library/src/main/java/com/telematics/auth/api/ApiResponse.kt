package com.telematics.auth.api

import com.google.gson.annotations.SerializedName

internal data class ApiResponse<out Result>(

	@SerializedName("Result")
	val result: Result?,
	@SerializedName("Status")
	val status: Int,
	@SerializedName("Title")
	val msg: String? = null,
	@SerializedName("Errors")
	val details: List<ErrorData>? = null
)
