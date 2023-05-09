package com.mbobiosio.eazychat.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class RemoteConfigRepoImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig
    private lateinit var repo: RemoteConfigRepoImpl

    @Before
    fun setup() {
        firebaseRemoteConfig = mockk(relaxed = true)
        repo = RemoteConfigRepoImpl(firebaseRemoteConfig)
    }

    @Test
    fun `getConfiguration should return valid data`() {
        every { firebaseRemoteConfig.getBoolean("force_update") } returns false
        every { firebaseRemoteConfig.getString("message") } returns "test message"
        every { firebaseRemoteConfig.getDouble("version_code") } returns 2.0

        val result = repo.getConfiguration()

        assertEquals(false, result.forceUpdate)
        assertEquals("test message", result.message)
        assertEquals(2.0, result.versionCode, 0.0)
    }

    @Test
    fun `getConfiguration should return RemoteConfigs from FirebaseRemoteConfig`() {
        // Given
        val forceUpdate = true
        val message = "New feature available"
        val versionCode = 1.0
        every { firebaseRemoteConfig.getBoolean("force_update") } returns forceUpdate
        every { firebaseRemoteConfig.getString("message") } returns message
        every { firebaseRemoteConfig.getDouble("version_code") } returns versionCode

        // When
        val configs = repo.getConfiguration()

        // Then
        assertEquals(RemoteConfigs(forceUpdate, message, versionCode), configs)
        verify(exactly = 1) { firebaseRemoteConfig.getBoolean("force_update") }
        verify(exactly = 1) { firebaseRemoteConfig.getString("message") }
        verify(exactly = 1) { firebaseRemoteConfig.getDouble("version_code") }
    }

    @Test
    fun `onConfigurationUpdate should fetch and activate Remote Config`() {
        // Given
        val task: Task<Boolean> = mockk()
        every { firebaseRemoteConfig.fetchAndActivate() } returns task
        every { task.addOnCompleteListener(any()) } returns task
        every { task.addOnFailureListener(any()) } returns task

        // When
        repo.onConfigurationUpdate()

        // Then
        verify(exactly = 1) { firebaseRemoteConfig.fetchAndActivate() }
        verify(exactly = 1) { task.addOnCompleteListener(any()) }
        verify(exactly = 1) { task.addOnFailureListener(any()) }
    }
}
