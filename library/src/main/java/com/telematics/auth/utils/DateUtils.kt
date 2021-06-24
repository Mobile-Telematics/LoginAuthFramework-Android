package com.telematics.auth.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
	fun checkDate(date: String): Boolean {
		return try {
			val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
			true
		} catch (e: Exception) {
			false
		}
	}
}