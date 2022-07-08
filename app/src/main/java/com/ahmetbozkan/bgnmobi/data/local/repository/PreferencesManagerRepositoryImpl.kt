package com.ahmetbozkan.bgnmobi.data.local.repository

import com.ahmetbozkan.bgnmobi.data.local.storage.PreferencesManager
import com.ahmetbozkan.bgnmobi.data.local.storage.model.AppInfo
import com.ahmetbozkan.bgnmobi.domain.repository.PreferencesManagerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesManagerRepositoryImpl @Inject constructor(
    private val manager: PreferencesManager
) : PreferencesManagerRepository {

    override val appInfoFlow: Flow<AppInfo>
        get() = manager.appInfoFlow

    override suspend fun storeFCMToken(token: String) {
        manager.storeFCMToken(token)
    }

}