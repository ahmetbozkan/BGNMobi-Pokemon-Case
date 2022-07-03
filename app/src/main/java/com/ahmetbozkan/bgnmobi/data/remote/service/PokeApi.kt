package com.ahmetbozkan.bgnmobi.data.remote.service

import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonsResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemons(): Response<GetPokemonsResponseModel>

}