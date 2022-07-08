package com.ahmetbozkan.bgnmobi.base

import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.core.Resource
import retrofit2.Response

abstract class BaseDataSource {

    /**
     * turn retrofit response wrapper to custom Resource wrapper to manage error & success states
     */
    protected suspend fun <T> handleRequest(callback: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = callback()
            Resource.success(response.body()!!)
        } catch (exception: Failure) {
            return Resource.error(null, exception)
        }
    }

}