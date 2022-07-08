package com.ahmetbozkan.bgnmobi.domain.usecase.storage

import com.ahmetbozkan.bgnmobi.domain.repository.PreferencesManagerRepository
import javax.inject.Inject

class StoreFCMTokenUseCase @Inject constructor(
    private val preferencesManagerRepository: PreferencesManagerRepository
) {

    suspend operator fun invoke(token: String) =
        preferencesManagerRepository.storeFCMToken(token)

}