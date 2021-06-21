package com.telematics.auth.library.external

interface ErrorListener {
	fun onError(error: Throwable)
}