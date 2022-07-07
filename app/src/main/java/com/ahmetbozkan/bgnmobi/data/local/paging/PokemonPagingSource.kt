package com.ahmetbozkan.bgnmobi.data.local.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmetbozkan.bgnmobi.core.Failure
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.data.local.mapper.GetPokemonsResponseMapper
import com.ahmetbozkan.bgnmobi.data.remote.datasource.PokeDataSource
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity

private const val STARTING_PAGE_INDEX = 0
private const val DEFAULT_LOAD_SIZE = 20

class PokemonPagingSource(
    private val dataSource: PokeDataSource,
    private val mapper: GetPokemonsResponseMapper,
    private val localError: (Failure) -> Unit
) : PagingSource<Int, PokemonEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val loadSize = params.loadSize

        return try {
            val response = dataSource.getPokemons(position, loadSize)

            if (response.status == Status.SUCCESS) {
                val pokemons = mapper.map(response.data!!).pokemons

                LoadResult.Page(
                    data = pokemons,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - loadSize,
                    nextKey = if (pokemons.isEmpty()) null else position + loadSize
                )
            } else {
                localError(response.error!!)
                LoadResult.Error(Throwable(response.error.message.orEmpty()))
            }
        } catch (exception: Exception) {
            localError(Failure.GeneralError(exception.message))
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(DEFAULT_LOAD_SIZE) ?: anchorPage?.nextKey?.minus(
                DEFAULT_LOAD_SIZE
            )
        }
    }
}