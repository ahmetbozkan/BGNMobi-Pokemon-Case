package com.ahmetbozkan.bgnmobi.data.local.mapper

import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonsResponseModel
import com.ahmetbozkan.bgnmobi.data.remote.model.Pokemon
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity
import com.ahmetbozkan.bgnmobi.domain.util.BaseMapper
import com.ahmetbozkan.bgnmobi.util.extensions.orEmpty
import com.ahmetbozkan.bgnmobi.util.extensions.orZero
import javax.inject.Inject

class GetPokemonsResponseMapper @Inject constructor() :
    BaseMapper<GetPokemonsResponseModel, GetPokemonsEntity> {

    override fun map(from: GetPokemonsResponseModel): GetPokemonsEntity {
        return GetPokemonsEntity(
            count = from.count.orZero(),
            pokemons = from.pokemons.getPokemonEntityList()
        )
    }

    private fun List<Pokemon>?.getPokemonEntityList(): List<PokemonEntity> {
        return if (this.isNullOrEmpty())
            listOf()
        else
            this.map { pokemon ->
                PokemonEntity(
                    name = pokemon.name.orEmpty(),
                    url = pokemon.url.orEmpty()
                )
            }
    }

}