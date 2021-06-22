package com.telematics.auth.external

fun interface SuccessListener<T> {
	fun onSuccess(result: T)
}