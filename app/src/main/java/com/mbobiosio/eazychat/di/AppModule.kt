package com.mbobiosio.eazychat.di

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.mbobiosio.eazychat.data.repository.RemoteConfigRepo
import com.mbobiosio.eazychat.data.repository.RemoteConfigRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return Firebase.remoteConfig
    }

    @Provides
    @Singleton
    fun bindConfig(
        remoteConfigRepoImpl: RemoteConfigRepoImpl,
        firebaseRemoteConfig: FirebaseRemoteConfig
    ): RemoteConfigRepo {
        remoteConfigRepoImpl.initRemoteConfiguration()
        remoteConfigRepoImpl.onConfigurationUpdate()
        return RemoteConfigRepoImpl(firebaseRemoteConfig)
    }

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics =
        FirebaseAnalytics.getInstance(context)
}