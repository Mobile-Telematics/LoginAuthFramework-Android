package com.telematicssdk.auth.external.results


/**
 * Represents user authorization result
 * @property accessToken JWT access token (Should be added to Authorization header)
 * @property refreshToken JWT refresh token
 */
data class LoginResult(val accessToken: String, val refreshToken: String)