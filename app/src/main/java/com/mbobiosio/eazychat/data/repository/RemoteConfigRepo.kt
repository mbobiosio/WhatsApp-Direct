package com.mbobiosio.eazychat.data.repository

import com.mbobiosio.eazychat.data.model.RemoteConfigs

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface RemoteConfigRepo {

    fun initRemoteConfiguration()

    fun getConfiguration(): RemoteConfigs

    fun onConfigurationUpdate()
}
