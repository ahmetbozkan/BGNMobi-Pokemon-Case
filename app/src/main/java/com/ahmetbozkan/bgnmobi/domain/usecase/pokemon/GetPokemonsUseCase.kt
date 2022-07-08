package com.ahmetbozkan.bgnmobi.domain.usecase.pokemon

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity
import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokeRepository
) {

    operator fun invoke(): LiveData<PagingData<PokemonEntity>> =
        repository.getPokemons()

}