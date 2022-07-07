package com.ahmetbozkan.bgnmobi.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity

interface PokeRepository {

    suspend fun getPokemonDetails(id: Int): Resource<PokemonDetailEntity>

    fun getPokemons(): LiveData<PagingData<PokemonEntity>>
}