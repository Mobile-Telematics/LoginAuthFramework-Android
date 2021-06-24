package com.telematics.auth.errors

import java.io.IOException

data class ApiException (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldErrorDetailsData>? = null

) : IOException()