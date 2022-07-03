package com.ahmetbozkan.bgnmobi.base

import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.core.Resource

abstract class BaseDataSource {

    protected suspend fun <T> handleOperation(call: suspend () -> T): Resource<T> {
        return try {
            val response = call()
            Resource.success(response)
        } catch (exception: Failure) {
            Resource.error(error = exception)
        }
    }

}