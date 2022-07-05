package com.ahmetbozkan.bgnmobi.core

import java.io.IOException

sealed class Failure : IOException() {

    data class HttpError(var code: Int, override var message: String) : Failure()
    data class TimeOutError(override var message: String?) : Failure()
    data class NoInternetError(override var message: String?) : Failure()
    data class EmptyResponse(override var message: String?) : Failure()
    data class GeneralError(override var message: String?) : Failure()
    object UnknownHostError : Failure()
}