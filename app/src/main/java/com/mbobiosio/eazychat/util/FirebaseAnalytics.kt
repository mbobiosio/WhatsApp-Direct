package com.mbobiosio.eazychat.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
fun FirebaseAnalytics.logEngagement(id: String, name: String, type: String) {
    logEvent(FirebaseAnalytics.Event.SELECT_ITEM, Bundle().apply {
        putString(FirebaseAnalytics.Param.ITEM_ID, id)
        putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        putString(FirebaseAnalytics.Param.CONTENT_TYPE, type)
    })
}

fun FirebaseAnalytics.logAppInstance() {
    logEvent(FirebaseAnalytics.Event.APP_OPEN, Bundle().apply {
        putString(FirebaseAnalytics.Param.SCREEN_NAME, "main")
        putString(FirebaseAnalytics.Param.SCREEN_CLASS, "App")
    })
}
