package com.telematics.auth.external.results


/**
 * Represents expired JWT access token refresh result
 * @param accessToken new refreshed JWT access token (Should be added to Authorization header)
 * @param accessToken new refreshed JWT refresh token
 */
data class RefreshResult(val accessToken: String, val refreshToken: String)