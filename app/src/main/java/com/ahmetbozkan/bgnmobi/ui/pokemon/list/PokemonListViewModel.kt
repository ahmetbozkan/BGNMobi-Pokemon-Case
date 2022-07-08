package com.ahmetbozkan.bgnmobi.ui.pokemon.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ahmetbozkan.bgnmobi.base.BaseViewModel
import com.ahmetbozkan.bgnmobi.domain.usecase.pokemon.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    getPokemonsUseCase: GetPokemonsUseCase
) : BaseViewModel() {

    val pokemons = getPokemonsUseCase.invoke()
        .cachedIn(viewModelScope + coroutineExceptionHandler)

}