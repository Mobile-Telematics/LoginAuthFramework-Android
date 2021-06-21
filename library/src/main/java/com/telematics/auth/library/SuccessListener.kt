package com.telematics.auth.library

interface SuccessListener<T> {
	fun onSuccess(result: T)
}