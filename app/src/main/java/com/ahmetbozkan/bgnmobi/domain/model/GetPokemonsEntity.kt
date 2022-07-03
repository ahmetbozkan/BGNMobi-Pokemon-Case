package com.ahmetbozkan.bgnmobi.domain.model

data class GetPokemonsEntity(
    val count: Int,
    val pokemons: List<PokemonEntity>
)

data class PokemonEntity(
    val name: String,
    val url: String
)
