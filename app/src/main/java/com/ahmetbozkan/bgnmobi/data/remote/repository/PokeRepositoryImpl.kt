package com.ahmetbozkan.bgnmobi.data.remote.repository

import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.data.local.mapper.GetPokemonsResponseMapper
import com.ahmetbozkan.bgnmobi.data.remote.datasource.PokeDataSource
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity
import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val dataSource: PokeDataSource,
    private val getPokemonsResponseMapper: GetPokemonsResponseMapper
) : PokeRepository {

    override suspend fun getPokemons(): Resource<GetPokemonsEntity> {
        val response = dataSource.getPokemons()

        return if (response.status == Status.SUCCESS) {
            Resource.success(data = getPokemonsResponseMapper.map(response.data!!))
        } else {
            Resource.error(error = response.error)
        }
    }

}