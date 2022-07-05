package com.ahmetbozkan.bgnmobi.domain.model


data class PokemonDetailEntity(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: PokemonSpriteEntity
)

data class PokemonSpriteEntity(
    val backDefault: String,
    val frontDefault: String
)
