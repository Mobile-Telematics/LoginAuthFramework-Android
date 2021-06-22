package com.telematics.auth.external


/**
 * Represents a Task to be processed
 */
class Task<T> {
	@Volatile
	private var successListener: SuccessListener<T>? = null

	@Volatile
	private var errorListener: ErrorListener? = null

	@Volatile
	private var result: T? = null

	@Volatile
	private var error: Throwable? = null

	/**
	 * Adds success callback to current [Task]
	 */
	@Synchronized
	fun onSuccess(callback: SuccessListener<T>) {
		successListener = callback
		result?.let {
			successListener?.onSuccess(it)
		}
	}

	/**
	 * Adds error catching callback to current [Task]
	 */
	@Synchronized
	fun onError(callback: ErrorListener) {
		errorListener = callback
		error?.let {
			errorListener?.onError(it)
		}
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

	/**
	 * Clears current [Task]
	 */
	@Synchronized
	fun clear() {
		result = null
		error = null
		successListener = null
		errorListener = null
	}
}