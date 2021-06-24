package com.telematics.auth.api.model

enum class MaritalStatus(val code: Int) {
	Married(1), // 1
	Widowed(2), // 2
	Divorced(3), // 3
	Single(4); // 4
	companion object {
		fun parse(string: String): MaritalStatus? {
			return try {
				val ordinal = string.toInt()
				values().find { it.code == ordinal }
			} catch (e: Exception) {
				null
			}
		}
	}
}