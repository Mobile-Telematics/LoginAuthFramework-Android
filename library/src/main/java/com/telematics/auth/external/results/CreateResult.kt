package com.telematics.auth.external.results

data class CreateResult(val deviceToken: String, val accessToken: String, val refreshToken: String)