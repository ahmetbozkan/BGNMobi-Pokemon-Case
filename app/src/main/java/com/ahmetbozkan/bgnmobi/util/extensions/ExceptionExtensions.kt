package com.ahmetbozkan.bgnmobi.util.extensions

import android.content.Context
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.core.Failure

fun Int.getHttpErrorMessage(context: Context): String {
    return when(this) {
        400 -> context.getString(R.string.error_http_400)
        401 -> context.getString(R.string.error_http_401)
        500 -> context.getString(R.string.error_http_500)
        else -> context.getString(R.string.error_general)
    }
}