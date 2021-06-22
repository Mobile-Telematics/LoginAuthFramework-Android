package com.telematics.auth.api.model.register


import com.google.gson.annotations.SerializedName

data class UserFields(
    @SerializedName("ClientId")
    val clientId: String?,
    @SerializedName("EnableLogging")
    val enableLogging: Boolean?,
    @SerializedName("EnableRealTimeLocation")
    val enableRealTimeLocation: Boolean?,
    @SerializedName("EnableTracking")
    val enableTracking: Boolean?,
    @SerializedName("Enabled")
    val enabled: Boolean?
)