package com.mbobiosio.eazychat.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import com.mbobiosio.eazychat.data.repository.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltViewModel
class RemoteConfigViewModel @Inject constructor(
    remoteConfigRepo: RemoteConfigRepo
): ViewModel() {

    val remoteConfigLiveData = MutableLiveData<RemoteConfigs>()

    init {
        remoteConfigLiveData.value = remoteConfigRepo.getConfiguration()
    }
}