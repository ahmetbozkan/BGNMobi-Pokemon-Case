package com.ahmetbozkan.bgnmobi.data.remote.util

import android.content.Context
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.util.extensions.getHttpErrorMessage
import com.ahmetbozkan.bgnmobi.util.extensions.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.io.EOFException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerInterceptor constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable())
            throw Failure.NoInternetError(context.getString(R.string.error_no_internet))

        val response = try {
            chain.proceed(chain.request())
        } catch (exception: Exception) {
            throw when (exception) {
                is UnknownHostException, is IllegalArgumentException -> Failure.UnknownHostError
                is HttpException -> Failure.HttpError(exception.code(), exception.code().getHttpErrorMessage(context))
                is SocketTimeoutException -> Failure.TimeOutError(exception.message)
                is EOFException -> Failure.EmptyResponse(context.getString(R.string.error_empty_response))

                else -> Failure.GeneralError(context.getString(R.string.error_general))
            }
        }

        return when (response.isSuccessful) {
            true -> {
                response.body?.let {
                    response
                } ?: run {
                    throw Failure.EmptyResponse(context.getString(R.string.error_empty_response))
                }

            }

            false -> throw Failure.HttpError(response.code, response.code.getHttpErrorMessage(context))
        }

    }

}