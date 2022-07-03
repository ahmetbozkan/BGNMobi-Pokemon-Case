package com.ahmetbozkan.bgnmobi.core

sealed class Failure : Exception() {

    data class HttpError(var code: Int, override var message: String) : Failure()
    data class TimeOutError(override var message: String?) : Failure()
    data class NoInternetError(override var message: String?) : Failure()
    data class EmptyResponse(override var message: String?) : Failure()
    data class GeneralError(override var message: String?) : Failure()
    object UnknownHostError : Failure()
}