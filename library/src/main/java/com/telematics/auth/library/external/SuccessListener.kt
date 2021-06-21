package com.telematics.auth.library.external

interface SuccessListener<T> {
	fun onSuccess(result: T)
}