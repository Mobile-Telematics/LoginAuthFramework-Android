package com.telematics.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.telematicssdk.auth.TelematicsAuth
import com.telematicssdk.auth.errors.ApiException

class MainActivity : AppCompatActivity() {
	companion object {
		private val TAG = "Authentication"

		private const val INSTANCE_ID = "your_instance_id"
		private const val INSTANCE_KEY = "your_instance_key"

		private const val DEVICE_TOKEN = "your_device_token"
		private const val ACCESS_TOKEN = "your_access_token"
		private const val REFRESH_TOKEN = "your_refresh_token"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<Button>(R.id.create_token).setOnClickListener {
			TelematicsAuth.createDeviceToken(
				instanceId = INSTANCE_ID,
				instanceKey = INSTANCE_KEY
			)
				.onSuccess { result ->
					Log.d(TAG, "result: $result")
				}
				.onError { e ->
					e.printStackTrace()
					if (e is ApiException) {
						Log.d(TAG, "error code: ${e.errorCode}, message: ${e.msg}")
					} else {
						Log.d(TAG, "message: ${e.message}")
					}
				}

		}

		findViewById<Button>(R.id.login).setOnClickListener {
			TelematicsAuth.login(
				instanceId = INSTANCE_ID,
				instanceKey = INSTANCE_KEY,
				deviceToken = DEVICE_TOKEN
			)
				.onSuccess { result ->
					Log.d(TAG, "result: $result")
				}
				.onError { e ->
					e.printStackTrace()
					if (e is ApiException) {
						Log.d(TAG, "error code: ${e.errorCode}, message: ${e.msg}")
					} else {
						Log.d(TAG, "message: ${e.message}")
					}
				}
		}

		findViewById<Button>(R.id.refresh_token).setOnClickListener {
			TelematicsAuth.refreshToken(
				instanceId = INSTANCE_ID,
				instanceKey = INSTANCE_KEY,
				accessToken = ACCESS_TOKEN,
				refreshToken = REFRESH_TOKEN
			)
				.onSuccess { result ->
					Log.d(TAG, "result: $result")
				}
				.onError { e ->
					e.printStackTrace()
					if (e is ApiException) {
						Log.d(TAG, "error code: ${e.errorCode}, message: ${e.msg}")
					} else {
						Log.d(TAG, "message: ${e.message}")
					}
				}
		}

		findViewById<Button>(R.id.refresh_or_login).setOnClickListener {
			TelematicsAuth.refreshTokenOrLogin(
				instanceId = INSTANCE_ID,
				instanceKey = INSTANCE_KEY,
				deviceToken = DEVICE_TOKEN,
				accessToken = ACCESS_TOKEN,
				refreshToken = REFRESH_TOKEN
			)
				.onSuccess { result ->
					Log.d(TAG, "result: $result")
				}
				.onError { e ->
					e.printStackTrace()
					if (e is ApiException) {
						Log.d(TAG, "error code: ${e.errorCode}, message: ${e.msg}")
					} else {
						Log.d(TAG, "message: ${e.message}")
					}
				}
		}

	}
}