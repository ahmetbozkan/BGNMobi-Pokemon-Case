package com.ahmetbozkan.bgnmobi.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.bgnmobi.base.BaseViewModel
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity
import com.ahmetbozkan.bgnmobi.domain.usecase.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BaseViewModel() {

    private val _pokemons = MutableLiveData<GetPokemonsEntity>()
    val pokemons: LiveData<GetPokemonsEntity> get() = _pokemons

    init {
        getPokemons()
    }

    private fun getPokemons() = viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        enableLoading()

        val result = getPokemonsUseCase.invoke()

        if (result.status == Status.SUCCESS) {
            disableLoading()
            _pokemons.postValue(result.data!!)
        } else manageException(result.error)

    }

}