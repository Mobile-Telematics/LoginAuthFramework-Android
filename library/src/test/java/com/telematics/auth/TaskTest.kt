package com.telematics.auth

import com.telematics.auth.external.Task
import org.junit.After
import org.junit.Before
import org.junit.Test

class TaskTest {

	private lateinit var task: Task<Any>

	@Before
	fun setUp() {
		task = Task()
	}

	@After
	fun tearDown() {
		task.clear()
	}

	@Test
	fun `test passing result before callback is attached`() {
		var successCalled = false
		var errorCalled = false
		task.success(Any())
		task.error(Throwable("Test throwable"))

		task
			.onSuccess {
				successCalled = true
			}
			.onError {
				errorCalled = true
			}
		assert(successCalled)
		assert(errorCalled)

	}

	@Test
	fun `test passing result after callback is attached`() {
		var successCalled = false
		var errorCalled = false
		task
			.onSuccess {
				successCalled = true
			}
			.onError {
				errorCalled = true
			}
		task.success(Any())
		task.error(Throwable("Test throwable"))
		assert(successCalled)
		assert(errorCalled)

	}
}