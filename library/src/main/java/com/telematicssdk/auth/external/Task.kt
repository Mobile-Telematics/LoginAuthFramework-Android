package com.telematicssdk.auth.external

import android.util.Log
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


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
	fun onSuccess(callback: SuccessListener<T>): Task<T> {
		successListener = callback
		result?.let {
			successListener?.onSuccess(it)
		}
		return this
	}

	/**
	 * Adds error catching callback to current [Task]
	 */
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

	suspend fun await(): T =
		suspendCoroutine { continuation ->
			onSuccess {
				continuation.resume(it)
			}
			onError {
				continuation.resumeWithException(it)
			}
		}
}