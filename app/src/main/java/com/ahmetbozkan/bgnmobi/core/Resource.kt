package com.ahmetbozkan.bgnmobi.core

data class Resource<out T>(val status: Status, val data: T?, val error: Failure?) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(
            status = Status.SUCCESS, data = data, error = null
        )

        fun <T> error(data: T? = null, error: Failure?): Resource<T> = Resource(
            status = Status.ERROR, data = data, error = error
        )
    }

}

enum class Status {
    SUCCESS,
    ERROR
}