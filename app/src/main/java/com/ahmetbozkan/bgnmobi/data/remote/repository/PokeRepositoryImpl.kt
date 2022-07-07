package com.ahmetbozkan.bgnmobi.data.remote.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.data.local.mapper.GetPokemonDetailsResponseMapper
import com.ahmetbozkan.bgnmobi.data.local.mapper.GetPokemonsResponseMapper
import com.ahmetbozkan.bgnmobi.data.local.paging.PokemonPagingSource
import com.ahmetbozkan.bgnmobi.data.remote.datasource.PokeDataSource
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val dataSource: PokeDataSource,
    private val getPokemonDetailsResponseMapper: GetPokemonDetailsResponseMapper,
    private val mapper: GetPokemonsResponseMapper
) : PokeRepository {

    override fun getPokemons() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PokemonPagingSource(dataSource, mapper) {
                Log.d("TAG", "getPokemons: ")
            }
        }
    ).liveData

    override suspend fun getPokemonDetails(id: Int): Resource<PokemonDetailEntity> {
        val response = dataSource.getPokemonDetails(id)

        return if (response.status == Status.SUCCESS)
            Resource.success(data = getPokemonDetailsResponseMapper.map(response.data!!))
        else
            Resource.error(error = response.error)

    }

}