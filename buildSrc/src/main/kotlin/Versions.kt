/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
object AndroidConfig {
    const val COMPILE_SDK = 34
    const val APPLICATION_ID = "com.mbobiosio.eazychat"
    const val MIN_ANDROID_SDK = 23
    const val TARGET_ANDROID_SDK = 34
    const val VERSION_CODE = 4
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    val versionName by lazy {
        val x = VERSION_CODE / 10_000
        val y = (VERSION_CODE % 10_000) / 100
        val z = VERSION_CODE % 100

        "$x.$y.$z"
    }
}

object Plugins {
    /*
    * Module Level
    */
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID = "android"
    const val KAPT = "kapt"
    const val PARCELIZE = "kotlin-parcelize"
    const val ANDROID_LIBRARY = "com.android.library"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val ANDROIDX_NAVIGATION = "androidx.navigation"
    const val GOOGLE_SERVICES = "com.google.gms.google-services"
    const val CRASHLYTICS = "com.google.firebase.crashlytics"
    const val DAGGER_HILT = "com.google.dagger.hilt.android"
}

object PluginVersion {
    const val AGP = "8.5.2"
    const val KOTLIN = "1.9.25"
    const val NAVIGATION = "2.8.5"
    const val GOOGLE_SERVICES = "4.4.2"
    const val CRASHLYTICS = "3.0.2"
    const val DAGGER_HILT = "2.51.1"
}
