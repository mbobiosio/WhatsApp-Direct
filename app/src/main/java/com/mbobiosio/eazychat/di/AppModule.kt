package com.mbobiosio.eazychat.di

import com.mbobiosio.eazychat.data.repository.RemoteConfigRepo
import com.mbobiosio.eazychat.data.repository.RemoteConfigRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun bindConfig(remoteConfigRepoImpl: RemoteConfigRepoImpl): RemoteConfigRepo {
        remoteConfigRepoImpl.initRemoteConfiguration()
        return RemoteConfigRepoImpl()
    }
}