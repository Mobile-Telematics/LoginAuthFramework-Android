package com.telematics.auth.external

fun interface ErrorListener {
	fun onError(error: Throwable)
}