package com.ahmetbozkan.bgnmobi.util.extensions

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.ahmetbozkan.bgnmobi.util.Constants.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

val Context.appInfoDataStore: DataStore<Preferences> by preferencesDataStore(name = "application_info")

fun DataStore<Preferences>.handleException(): Flow<Preferences> =
    this.data.catch { exception ->
        if (exception is IOException) {
            Log.e(TAG, "Error reading preferences.", exception)
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }