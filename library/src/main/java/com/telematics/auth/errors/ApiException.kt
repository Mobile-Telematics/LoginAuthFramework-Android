package com.telematics.auth.errors

import java.io.IOException

/**
 * Telematics API exception
 *
 * @param errorCode html-code of the error
 * @param msg message og the error
 * @param details detailed info about error
 */
data class ApiException (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldErrorDetailsData>? = null

) : IOException()