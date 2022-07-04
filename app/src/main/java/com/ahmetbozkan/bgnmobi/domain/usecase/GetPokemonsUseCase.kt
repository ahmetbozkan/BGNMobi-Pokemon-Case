package com.ahmetbozkan.bgnmobi.domain.usecase

import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokeRepository
) {

    suspend operator fun invoke() =
        repository.getPokemons()

}