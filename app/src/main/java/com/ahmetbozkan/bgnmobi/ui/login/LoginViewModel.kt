package com.ahmetbozkan.bgnmobi.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.bgnmobi.base.BaseViewModel
import com.ahmetbozkan.bgnmobi.domain.usecase.storage.StoreFCMTokenUseCase
import com.ahmetbozkan.bgnmobi.util.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val storeFCMTokenUseCase: StoreFCMTokenUseCase
) : BaseViewModel() {

    init {
        registerFCM()
    }

    private fun registerFCM() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(Constants.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result
            storeFCMToken(token)
        })
    }

    private fun storeFCMToken(token: String) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            storeFCMTokenUseCase.invoke(token)
        }

}

