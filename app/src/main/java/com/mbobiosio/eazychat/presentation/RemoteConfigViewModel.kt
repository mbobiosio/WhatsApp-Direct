package com.mbobiosio.eazychat.presentation

import androidx.lifecycle.ViewModel
import com.mbobiosio.eazychat.data.model.RemoteConfigs
import com.mbobiosio.eazychat.data.repository.RemoteConfigRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltViewModel
class RemoteConfigViewModel @Inject constructor(
    remoteConfigRepo: RemoteConfigRepo
) : ViewModel() {

    private val _remoteConfigStateFlow = MutableStateFlow(remoteConfigRepo.getConfiguration())
    val remoteConfigStateFlow: StateFlow<RemoteConfigs> = _remoteConfigStateFlow
}
