package com.mbobiosio.eazychat.data.repository

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mbobiosio.eazychat.BuildConfig
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import com.mbobiosio.eazychat.util.DefaultConfigs
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class RemoteConfigRepoImpl @Inject constructor() : RemoteConfigRepo {

    //Get RemoteConfig Instance
    private val remoteConfig = Firebase.remoteConfig

    override fun initRemoteConfiguration() {
        /**
         * [cacheInterval] defines the interval of fetches per hour.
         * Use [remoteConfigSettings] to set the minimum fetch interval
         * */
        val cacheInterval = 3000L // 3000 milliseconds Long equivalent of 3 seconds
        val minFetchInterval: Long = if (BuildConfig.DEBUG) {
            0
        } else {
            cacheInterval
        }
        val configSettings = remoteConfigSettings {
            fetchTimeoutInSeconds = 20L
            minimumFetchIntervalInSeconds = minFetchInterval
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

        /**
         * Fetch updates from Firebase console
         * */
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("Successful ${it.result}")
                } else {
                    Timber.d("Failed ${it.result}")
                }
            }.addOnFailureListener {
                Timber.d("Exception ${it.message}")
            }
        // [End fetch and activate]
    }

    override fun getConfiguration(): RemoteConfigs {
        return RemoteConfigs(
            force_update = remoteConfig.getBoolean("force_update"),
            message = remoteConfig.getString("message"),
            version_code = remoteConfig.getDouble("version_code")
        )
    }
}