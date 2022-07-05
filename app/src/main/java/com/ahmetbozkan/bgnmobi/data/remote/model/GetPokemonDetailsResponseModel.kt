package com.ahmetbozkan.bgnmobi.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetPokemonDetailsResponseModel(
    val id: Int?,
    val name: String?,
    val weight: Int?,
    val height: Int?,
    val sprites: PokemonSprite?
)

data class PokemonSprite(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("front_default") val frontDefault: String?
)