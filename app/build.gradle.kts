plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.ANDROID)
    kotlin(Plugins.KAPT)
    id(Plugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdk = AndroidConfig.MIN_ANDROID_SDK
        targetSdk = AndroidConfig.TARGET_ANDROID_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
    namespace = "com.mbobiosio.whatsapp"
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${PluginVersion.NAVIGATION}")
    implementation("androidx.navigation:navigation-ui-ktx:${PluginVersion.NAVIGATION}")

    implementation("com.hbb20:ccp:2.7.0")
    implementation("com.github.Spikeysanju:MotionToast:1.4")
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}