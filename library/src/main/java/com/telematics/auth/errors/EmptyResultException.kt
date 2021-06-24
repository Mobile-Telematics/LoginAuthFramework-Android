package com.telematics.auth.errors


/**
 * Kind of Telematics API exception.
 *
 * Occurs when server returns empty result
 */
class EmptyResultException: Throwable("Result is empty")