package com.mbobiosio.eazychat

import android.app.Application
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.mbobiosio.eazychat.util.logAppInstance
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltAndroidApp
class EazyChatApp : Application() {

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        firebaseAnalytics.logAppInstance()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}