package com.telematicssdk.auth.errors

import java.io.IOException

/**
 * Telematics API exception
 *
 * @property errorCode html-code of the error
 * @property msg message og the error
 * @property details detailed info about error
 */
data class ApiException (
        val errorCode: Int,
        val msg: String? = null,
        val details: List<FieldErrorDetailsData>? = null

) : IOException()