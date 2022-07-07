package com.ahmetbozkan.bgnmobi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmetbozkan.bgnmobi.core.Failure
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        _error.postValue(
            Failure.GeneralError(t.message)
        )
    }

    private val _error = MutableLiveData<Failure?>()
    val error: LiveData<Failure?> get() = _error

    protected fun manageException(error: Failure?) {
        _error.postValue(error)
    }

}