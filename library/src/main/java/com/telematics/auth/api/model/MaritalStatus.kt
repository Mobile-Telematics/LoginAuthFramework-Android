package com.telematics.auth.api.model

enum class MaritalStatus {
	Married,
	Widowed,
	Divorced,
	Single;
	companion object {
		fun parse(string: String): MaritalStatus? {
			return try {
				val ordinal = string.toInt()
				values()[ordinal]
			} catch (e: Exception) {
				null
			}
		}
	}
}