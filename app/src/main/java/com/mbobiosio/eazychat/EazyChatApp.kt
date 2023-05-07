package com.mbobiosio.eazychat

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltAndroidApp
class EazyChatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        firebaseAnalytics = Firebase.analytics

        trackAppOpen()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun trackAppOpen() {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "main")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "App")
        }
    }

    companion object {
        private lateinit var firebaseAnalytics: FirebaseAnalytics

        fun trackSendMessageEvent(id: String, name: String, type: String) {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_ID, id)
                param(FirebaseAnalytics.Param.ITEM_NAME, name)
                param(FirebaseAnalytics.Param.CONTENT_TYPE, type)
            }
        }
    }
}