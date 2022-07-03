package com.ahmetbozkan.bgnmobi.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetPokemonsResponseModel(
    val count: Int?,
    @SerializedName("results") val pokemons: List<Pokemon>?
)

data class Pokemon(
    val name: String?,
    val url: String?
)
