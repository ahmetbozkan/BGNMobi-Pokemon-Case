package com.ahmetbozkan.bgnmobi.domain.usecase

import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val repository: PokeRepository
) {

    suspend operator fun invoke(id: Int): Resource<PokemonDetailEntity> =
        repository.getPokemonDetails(id)

}