package com.ahmetbozkan.bgnmobi.data.remote.service

import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonDetailsResponseModel
import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemons(): Response<GetPokemonsResponseModel>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): Response<GetPokemonDetailsResponseModel>

}