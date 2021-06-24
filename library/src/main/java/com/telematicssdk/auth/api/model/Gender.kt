package com.telematicssdk.auth.api.model

enum class Gender {
	Male,
	Female,
	None;
	companion object {
		fun parse(string: String): Gender?{
			return try {
				valueOf(string)
			} catch (e: Exception) {
				null
			}
		}
	}
}