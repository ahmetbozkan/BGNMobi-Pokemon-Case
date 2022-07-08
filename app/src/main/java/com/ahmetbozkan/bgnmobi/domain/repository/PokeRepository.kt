package com.ahmetbozkan.bgnmobi.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity

interface PokeRepository {

    /**
     * get pokemon details with the pokemonId
     * @param id is the pokemonId (adapter position in this case bcs id field does not exist in pokemon list response)
     */
    suspend fun getPokemonDetails(id: Int): Resource<PokemonDetailEntity>

    /**
     * get all the pokemons from the pokeapi
     * @return pokemon entity as PagingData wrapped in LiveData
     * Pokemons wrapped in PagingData bcs pokemons will not be used in normal RecyclerViewAdapter instead +
     * will be used in PagingListAdapter to handle pagination
     */
    fun getPokemons(): LiveData<PagingData<PokemonEntity>>
}