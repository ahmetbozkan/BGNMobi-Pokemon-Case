package com.ahmetbozkan.bgnmobi.ui.pokemon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.bgnmobi.base.BaseViewModel
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.usecase.pokemon.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : BaseViewModel() {

    private val _pokemon = MutableLiveData<Resource<PokemonDetailEntity>>()
    val pokemon: LiveData<Resource<PokemonDetailEntity>> get() = _pokemon

    fun getPokemonDetails(id: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _pokemon.postValue(Resource.loading())

            val result = getPokemonDetailsUseCase.invoke(id)

            when (result.status) {
                Status.SUCCESS -> _pokemon.postValue(result)
                Status.ERROR -> {
                    _pokemon.postValue(Resource.error(null, result.error))
                    manageException(result.error)
                }
                Status.LOADING -> _pokemon.postValue(Resource.loading())
            }
        }

}