package com.ahmetbozkan.bgnmobi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmetbozkan.bgnmobi.core.Failure
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        disableLoading()
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<Failure?>()
    val error: LiveData<Failure?> get() = _error

    protected fun enableLoading() {
        _isLoading.postValue(true)
    }

    protected fun disableLoading() {
        if (isLoading.value == true)
            _isLoading.postValue(false)
    }

    protected fun manageException(error: Failure?) {
        disableLoading()
        _error.postValue(error)
    }

}