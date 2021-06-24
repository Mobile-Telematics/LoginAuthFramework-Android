package com.telematicssdk.auth

import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.api.model.MaritalStatus
import com.telematicssdk.auth.errors.ApiException
import org.junit.Test
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class AuthDelegateTest {
	private val delegate = AuthDelegate(
		"https://user.telematicssdk.com/",
		GsonConverterFactory.create()
	)
	private val instanceId = "abe4f21d-2af1-48b0-a777-d05a1a9f43c8"
	private val instanceKey = "52f37202-6657-40e7-b438-13419b15c8d1"
	private lateinit var deviceToken: String
	private lateinit var accessToken: String
	private lateinit var refreshToken: String

//	@Before
//	fun setUp() {
//	}
//
//	@After
//	fun tearDown() {
//	}

	@Test
	fun createDeviceTokenWithEmptyFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createDeviceTokenWithWrongEmailFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			"fhfhfhfhf",
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createDeviceTokenWithCorrectEmailFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			"mzmzmz@mail.com",
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createDeviceTokenWithWrongPhoneFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			null,
			"+798888",
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createDeviceTokenWithCorrectPhoneFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			null,
			"+798888998877",
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createDeviceTokenWithFields() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			"gfgfgf@mail.ru",
			"+798888998871",
			"121212",
			"M",
			"Z",
			"2020-12-01T20:00:00",
			MaritalStatus.Married,
			1,
			"RSO",
			Gender.Male
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
	}

	@Test
	fun createUserAndGetProfile() {
		var waitLock = CountDownLatch(1)
		delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			"gfgfgf@mail.ru",
			"+798888998871",
			"121212",
			"M",
			"Z",
			"2020-12-01T20:00:00",
			MaritalStatus.Married,
			1,
			"RSO",
			Gender.Male
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await()
		waitLock = CountDownLatch(1)
		delegate.getUserProfile(
			instanceId = instanceId,
			instanceKey = instanceKey,
			accessToken = accessToken
		).onSuccess { profile ->
			assert(profile.address == "RSO")
			assert(profile.deviceToken == deviceToken)
			assert(profile.birthDay == "2020-12-01T20:00:00")
			assert(profile.firstName == "M")
			assert(profile.lastName == "Z")
			assert(profile.childrenCount == 1)
			assert(profile.email == "gfgfgf@mail.ru")
			assert(profile.phone == "+798888998871")
			assert(profile.clientId == "121212")
			assert(profile.maritalStatus == MaritalStatus.Married)
			assert(profile.gender == Gender.Male)
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await(7, TimeUnit.SECONDS)
	}

	@Test
	fun login() {
		val waitLock = CountDownLatch(1)
		val deviceToken = "1bc853ff-0d65-4373-a5b1-ab4d4cf7119f"
		delegate.login(
			instanceId = instanceId,
			instanceKey = instanceKey,
			deviceToken = deviceToken
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it !is ApiException)
			waitLock.countDown()
		}
		waitLock.await(10, TimeUnit.SECONDS)
	}


	@Test
	fun updateUserProfile() {
		var waitLock = CountDownLatch(1)
		delegate.createDeviceToken(
			instanceId, instanceKey, null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
		)
			.onSuccess {
				println(it)
				assert(it.accessToken.isNotBlank())
				assert(it.refreshToken.isNotBlank())
				assert(it.deviceToken.isNotBlank())
				deviceToken = it.deviceToken
				accessToken = it.accessToken
				refreshToken = it.refreshToken
				waitLock.countDown()
			}
			.onError {
				waitLock.countDown()
			}
		waitLock.await(20, TimeUnit.SECONDS)
		waitLock = CountDownLatch(1)
		delegate.updateUserProfile(
			instanceId,
			instanceKey,
			accessToken,
			deviceToken,
			"mmmm@mail.ru",
			"+79888888888",
			"9293",
			"M",
			"Z",
			"2020-12-01T20:00:00",
			MaritalStatus.Married,
			10,
			"RSO",
			Gender.Male
		)
			.onSuccess {
				println("success")
				waitLock.countDown()
			}
			.onError {
				println("error: ${it.message}")
				waitLock.countDown()
			}
		waitLock.await(20, TimeUnit.SECONDS)
		waitLock = CountDownLatch(1)
		delegate.getUserProfile(instanceId, instanceKey, accessToken)
			.onSuccess { profile ->
				println(profile)
				assert(profile.address == "RSO")
				assert(profile.deviceToken == deviceToken)
				assert(profile.birthDay == "2020-12-01T20:00:00")
				assert(profile.firstName == "M")
				assert(profile.lastName == "Z")
				assert(profile.childrenCount == 10)
				assert(profile.email == "mmmm@mail.ru")
				assert(profile.phone == "+79888888888")
				assert(profile.clientId == "9293")
				assert(profile.maritalStatus == MaritalStatus.Married)
				assert(profile.gender == Gender.Male)
				waitLock.countDown()
			}
			.onError {
				waitLock.countDown()
			}
		waitLock.await(20, TimeUnit.SECONDS)


	}

	@Test
	fun checkWrongBirthdayInUserRegister() {
		val waitLock = CountDownLatch(1)
		val task = delegate.createDeviceToken(
			instanceId = instanceId,
			instanceKey = instanceKey,
			null,
			null,
			null,
			null,
			null,
			"2020-12-01T00:00:00",
			null,
			null,
			null,
			null
		).onSuccess {
			assert(it.accessToken.isNotBlank())
			assert(it.refreshToken.isNotBlank())
			assert(it.deviceToken.isNotBlank())
			deviceToken = it.deviceToken
			accessToken = it.accessToken
			refreshToken = it.refreshToken
			waitLock.countDown()
		}.onError {
			assert(it is IllegalArgumentException)
			waitLock.countDown()
		}
		waitLock.await()
	}
}