package com.telematics.auth.external.results


/**
 * Represents user authorization result
 * @param accessToken JWT access token (Should be added to Authorization header)
 * @param accessToken JWT refresh token
 */
data class LoginResult(val accessToken: String, val refreshToken: String)