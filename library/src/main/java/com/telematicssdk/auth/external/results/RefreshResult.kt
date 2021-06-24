package com.telematicssdk.auth.external.results


/**
 * Represents expired JWT access token refresh result
 * @property accessToken new refreshed JWT access token (Should be added to Authorization header)
 * @property accessToken new refreshed JWT refresh token
 */
data class RefreshResult(val accessToken: String, val refreshToken: String)