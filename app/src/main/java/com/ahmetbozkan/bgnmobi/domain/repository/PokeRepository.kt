package com.ahmetbozkan.bgnmobi.domain.repository

import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity

interface PokeRepository {

    suspend fun getPokemons(): Resource<GetPokemonsEntity>

}