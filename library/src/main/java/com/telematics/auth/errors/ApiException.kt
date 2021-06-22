package com.telematics.auth.errors

data class ApiException (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldErrorDetailsData>? = null

) : Throwable()