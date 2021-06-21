package com.telematics.auth.library.external


class Task<T> {
	@Volatile
	private var successListener: SuccessListener<T>? = null
	@Volatile
	private var errorListener: ErrorListener? = null

	@Volatile
	private var result: T? = null
	@Volatile
	private var error: Throwable? = null

	@Synchronized
	fun onSuccess(callback: SuccessListener<T>): Task<T> {
		successListener = callback
		result?.let {
			successListener?.onSuccess(it)
		}
		return this
	}
	@Synchronized
	fun onError(callback: ErrorListener): Task<T> {
		errorListener = callback
		error?.let {
			errorListener?.onError(it)
		}
		return this
	}
	@Synchronized
	internal fun success(result: T) {
		this.result = result
		successListener?.onSuccess(result)
	}
	@Synchronized
	internal fun error(error: Throwable) {
		this.error = error
		errorListener?.onError(error)
	}
	@Synchronized
	fun clear() {
		result = null
		error = null
		successListener = null
		errorListener = null
	}
}