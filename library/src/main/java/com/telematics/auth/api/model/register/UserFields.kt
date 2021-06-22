package com.telematics.auth.api.model.register


import com.google.gson.annotations.SerializedName

data class UserFields(
    @SerializedName("ClientId")
    val clientId: String? = null,
    @SerializedName("EnableLogging")
    val enableLogging: Boolean? = null,
    @SerializedName("EnableRealTimeLocation")
    val enableRealTimeLocation: Boolean? = null,
    @SerializedName("EnableTracking")
    val enableTracking: Boolean? = null,
    @SerializedName("Enabled")
    val enabled: Boolean? = null
)