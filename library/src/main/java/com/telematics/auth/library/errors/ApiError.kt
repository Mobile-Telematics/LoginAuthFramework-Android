package com.telematics.auth.library.errors

data class ApiError (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldErrorDetailsData>? = null

) : Throwable()