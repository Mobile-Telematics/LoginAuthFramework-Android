package com.telematics.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.telematicssdk.auth.TelematicsAuth

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<Button>(R.id.button).setOnClickListener {
			TelematicsAuth.createDeviceToken(
				instanceId = "abe4f21d-2af1-48b0-a777-d05a1a9f43c8",
				instanceKey = "52f37202-6657-40e7-b438-13419b15c8d1"
			)
				.onSuccess { result ->
					Toast.makeText(this,result.toString(), Toast.LENGTH_SHORT).show()
				}
				.onError { e ->
					e.printStackTrace()
					Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
				}

		}

	}
}