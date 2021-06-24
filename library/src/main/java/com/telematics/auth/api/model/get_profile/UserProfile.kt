package com.telematics.auth.api.model.get_profile


import com.google.gson.annotations.SerializedName

internal data class UserProfile(
    @SerializedName("Address")
    val address: String? = null,
    @SerializedName("Birthday")
    val birthday: String? = null,
    @SerializedName("ChildrenCount")
    val childrenCount: Int? = null,
    @SerializedName("Email")
    val email: String? = null,
    @SerializedName("FirstName")
    val firstName: String? = null,
    @SerializedName("Gender")
    val gender: String? = null,
    @SerializedName("LastName")
    val lastName: String? = null,
    @SerializedName("MaritalStatus")
    val maritalStatus: String? = null,
    @SerializedName("Phone")
    val phone: String? = null
)