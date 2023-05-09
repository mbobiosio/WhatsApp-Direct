package com.mbobiosio.eazychat.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mbobiosio.eazychat.BuildConfig
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import com.mbobiosio.eazychat.util.CACHE_INTERVAL
import com.mbobiosio.eazychat.util.DefaultConfigs
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
open class RemoteConfigRepoImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepo {

    override fun initRemoteConfiguration() {
        /**
         * [cacheInterval] defines the interval of fetches per hour.
         * Use [remoteConfigSettings] to set the minimum fetch interval
         * */

        val configSettings = remoteConfigSettings {
            fetchTimeoutInSeconds = 20L
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else CACHE_INTERVAL
        }
        // [END config settings]

        /*
        * Set the default parameters for Remote Config
        * Your app will use these default values until there's a change in the firebase console
        * */
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DefaultConfigs.getDefaultParams())
        }
        // [END default config]
    }

    override fun getConfiguration() = RemoteConfigs(
        forceUpdate = remoteConfig.getBoolean("force_update"),
        message = remoteConfig.getString("message"),
        versionCode = remoteConfig.getDouble("version_code")
    )

    override fun onConfigurationUpdate() {
        /**
         * Fetch updates from Firebase console
         * */
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("Successful with new update ${task.result}")
                } else {
                    Timber.d("Failed ${task.result}")
                }
            }.addOnFailureListener { e ->
                Timber.d("Exception ${e.message}")
            }
        // [End fetch and activate]
    }
}
