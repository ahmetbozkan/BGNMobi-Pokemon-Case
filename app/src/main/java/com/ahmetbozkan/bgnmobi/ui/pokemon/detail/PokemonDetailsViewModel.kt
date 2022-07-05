package com.ahmetbozkan.bgnmobi.ui.pokemon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetbozkan.bgnmobi.base.BaseViewModel
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.usecase.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : BaseViewModel() {

    private val _pokemon = MutableLiveData<PokemonDetailEntity>()
    val pokemon: LiveData<PokemonDetailEntity> get() = _pokemon

    fun getPokemonDetails(id: Int) =
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            enableLoading()

            val result = getPokemonDetailsUseCase.invoke(id)

            when (result.status) {
                Status.SUCCESS -> {
                    _pokemon.postValue(result.data!!)
                    disableLoading()
                }
                Status.ERROR -> manageException(result.error)
            }
        }

}