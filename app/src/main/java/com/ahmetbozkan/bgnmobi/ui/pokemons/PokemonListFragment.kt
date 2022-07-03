package com.ahmetbozkan.bgnmobi.ui.pokemons

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseFragment
import com.ahmetbozkan.bgnmobi.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokemonListViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_list

    override val viewModel: PokemonListViewModel by viewModels()

    override fun initialize(savedInstanceState: Bundle?) {

    }


}