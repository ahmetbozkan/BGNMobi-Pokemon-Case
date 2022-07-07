package com.ahmetbozkan.bgnmobi.data.remote.repository

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
import com.ahmetbozkan.bgnmobi.util.Constants
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val dataSource: PokeDataSource,
    private val getPokemonDetailsResponseMapper: GetPokemonDetailsResponseMapper,
    private val mapper: GetPokemonsResponseMapper
) : PokeRepository {

    override fun getPokemons() = Pager(
        config = PagingConfig(
            pageSize = Constants.DEFAULT_PAGE_SIZE,
            maxSize = Constants.DEFAULT_MAX_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonPagingSource(dataSource, mapper) }
    ).liveData

    override suspend fun getPokemonDetails(id: Int): Resource<PokemonDetailEntity> {
        val response = dataSource.getPokemonDetails(id)

        return if (response.status == Status.SUCCESS)
            Resource.success(data = getPokemonDetailsResponseMapper.map(response.data!!))
        else
            Resource.error(error = response.error)

    }

}