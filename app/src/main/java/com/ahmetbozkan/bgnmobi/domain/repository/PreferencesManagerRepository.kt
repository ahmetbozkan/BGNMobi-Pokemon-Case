package com.ahmetbozkan.bgnmobi.domain.repository

import com.ahmetbozkan.bgnmobi.data.local.storage.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface PreferencesManagerRepository {

    /**
     * app info's retrieved from datastore as Flow object
     */
    val appInfoFlow: Flow<AppInfo>

    /**
     * store fcm token retrieved in LoginViewModel in starting of the app
     * @param token from fcm
     */
    suspend fun storeFCMToken(token: String)


}