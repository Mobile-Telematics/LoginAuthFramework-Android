package com.telematics.auth.external.results


/**
 * Represents new user registration result
 * @param deviceToken Device token of the user
 * @param accessToken JWT access token (Should be added to Authorization header)
 * @param accessToken JWT refresh token
 */
data class CreateResult(val deviceToken: String, val accessToken: String, val refreshToken: String)