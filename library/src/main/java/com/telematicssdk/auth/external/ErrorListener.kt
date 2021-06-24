package com.telematicssdk.auth.external

/**
 * Functional callback about error.
 *
 * Should be added to [Task.onError] method
 */
fun interface ErrorListener {
	fun onError(error: Throwable)
}