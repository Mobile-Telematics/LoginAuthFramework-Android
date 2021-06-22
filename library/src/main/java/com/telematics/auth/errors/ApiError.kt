package com.telematics.auth.errors

data class ApiError (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldDetailsDataError>? = null

) : Throwable()