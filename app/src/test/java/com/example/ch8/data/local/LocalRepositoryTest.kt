package com.example.ch8.data.local

import androidx.work.WorkManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

class LocalRepositoryTest {
    private val dataManager = mock<DataStoreManager>()
    private val workManager = mock<WorkManager>()

    private val repository =
        LocalRepository(
            dataStoreManager = dataManager,
            workManager = workManager,
        )

    @Test
    fun `validate input return true`() =
        runTest {
            val username = "username"
            val password = "password"
            val expected = true

            val actual = repository.validateInput(username, password)

            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `validate input return false`() =
        runTest {
            val username = ""
            val password = ""
            val expected = false

            val actual = repository.validateInput(username, password)

            Assert.assertEquals(expected, actual)
        }

    @Test
    fun `authenticate return token`() =
        runTest {
            val username = "febi"
            val password = "123456"
            val expected = "token"

            val actual = repository.authenticate(username, password)

            Assert.assertEquals(expected, actual)
        }

    @Test(expected = UnsupportedOperationException::class)
    fun `authenticate return throw`() =
        runTest {
            val username = "username"
            val password = "aiueo"

            repository.authenticate(username, password)
        }
}
