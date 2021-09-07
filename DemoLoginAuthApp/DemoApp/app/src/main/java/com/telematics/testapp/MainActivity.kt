package com.telematics.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telematicssdk.auth.TelematicsAuth
import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.api.model.MaritalStatus

class MainActivity : AppCompatActivity() {

    private val INSTANCE_ID = "your_instance_id"
    private val INSTANCE_KEY = "your_instance_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createDeviceToken() {

        TelematicsAuth.createDeviceToken(
            INSTANCE_ID,
            INSTANCE_KEY
        )
            .onSuccess{
                it.deviceToken
            }
            .onError{

            }
    }

    fun login(deviceToken: String) {

        TelematicsAuth.login(
            INSTANCE_ID,
            INSTANCE_KEY,
            deviceToken
        )
            .onSuccess{
                it.accessToken
                it.refreshToken
            }
            .onError{

            }
    }

    fun getUser(accessToken: String) {

        TelematicsAuth.getUserProfile(
            INSTANCE_ID,
            INSTANCE_KEY,
            accessToken
        )
            .onSuccess{
                it.firstName
            }
            .onError{

            }
    }

    fun updateUser(deviceToken: String, accessToken: String){

        TelematicsAuth.updateUserProfile(
            INSTANCE_ID,
            INSTANCE_KEY,
            deviceToken,
            accessToken,
            "email",
            "phone",
            "clientId",
            "firstName",
            "lastName",
            "birthDay", //format: yyyy-MM-dd'T'HH:mm:ss
        MaritalStatus.Divorced,
            0,
            "address",
            Gender.None
        )
            .onSuccess{

            }
            .onError{

            }
    }

    fun refreshToken(accessToken: String, refreshToken: String){

        TelematicsAuth.refreshToken(
            INSTANCE_ID,
            INSTANCE_KEY,
            accessToken,
            refreshToken
        )
            .onSuccess{
                it.refreshToken
            }
            .onError{
                
            }
    }
}