package com.telematicssdk.auth.external


/**
 * Functional callback about success.
 *
 * Should be added to [Task.onSuccess] method
 */
fun interface SuccessListener<T> {
	fun onSuccess(result: T)
}