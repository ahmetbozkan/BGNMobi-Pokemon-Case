package com.ahmetbozkan.bgnmobi.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahmetbozkan.bgnmobi.data.local.storage.model.AppInfo
import com.ahmetbozkan.bgnmobi.util.extensions.EMPTY
import com.ahmetbozkan.bgnmobi.util.extensions.appInfoDataStore
import com.ahmetbozkan.bgnmobi.util.extensions.handleException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val appInfoStore: DataStore<Preferences> = context.appInfoDataStore

    val appInfoFlow = appInfoStore.handleException()
        .map { preferences ->
            val fcmToken = preferences[PreferencesKeys.FCM_TOKEN] ?: String.EMPTY

            AppInfo(fcmToken = fcmToken)
        }


    suspend fun storeFCMToken(token: String) {
        appInfoStore.edit { preferences ->
            preferences[PreferencesKeys.FCM_TOKEN] = token
        }
    }
}

private object PreferencesKeys {
    val FCM_TOKEN = stringPreferencesKey("key_fcm_token")
}