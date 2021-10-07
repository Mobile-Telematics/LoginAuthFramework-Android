package com.telematicssdk.auth.utils

import java.text.SimpleDateFormat
import java.util.*

internal object DateUtils {
	fun checkDate(date: String): Boolean {
		return try {
			val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
			format.parse(date)
			true
		} catch (e: Exception) {
			false
		}
	}
}