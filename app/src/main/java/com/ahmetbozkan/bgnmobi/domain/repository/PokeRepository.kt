package com.ahmetbozkan.bgnmobi.domain.repository

import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity

interface PokeRepository {

    suspend fun getPokemons(): Resource<GetPokemonsEntity>

    suspend fun getPokemonDetails(id: Int): Resource<PokemonDetailEntity>

}