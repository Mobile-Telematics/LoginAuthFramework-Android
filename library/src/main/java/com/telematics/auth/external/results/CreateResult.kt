package com.telematics.auth.external.results


/**
 * Represents new user registration result
 * @property deviceToken Device token of the user
 * @property accessToken JWT access token (Should be added to Authorization header)
 * @property accessToken JWT refresh token
 */
data class CreateResult(val deviceToken: String, val accessToken: String, val refreshToken: String)