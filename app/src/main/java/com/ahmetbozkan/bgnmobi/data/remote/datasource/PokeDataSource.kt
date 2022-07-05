package com.ahmetbozkan.bgnmobi.data.remote.datasource

import com.ahmetbozkan.bgnmobi.base.BaseDataSource
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonDetailsResponseModel
import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonsResponseModel
import com.ahmetbozkan.bgnmobi.data.remote.service.PokeApi
import javax.inject.Inject

class PokeDataSource @Inject constructor(
    private val api: PokeApi
) : BaseDataSource() {

    suspend fun getPokemons(): Resource<GetPokemonsResponseModel> =
        handleRequest {
            api.getPokemons()
        }

    suspend fun getPokemonDetails(id: Int): Resource<GetPokemonDetailsResponseModel> =
        handleRequest {
            api.getPokemonDetails(id)
        }

}