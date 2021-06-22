package com.telematics.auth.external

interface ErrorListener {
	fun onError(error: Throwable)
}