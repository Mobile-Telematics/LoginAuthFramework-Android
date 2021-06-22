package com.telematics.auth.external

interface SuccessListener<T> {
	fun onSuccess(result: T)
}