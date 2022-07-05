package com.ahmetbozkan.bgnmobi.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseFragment
import com.ahmetbozkan.bgnmobi.databinding.FragmentPokemonListBinding
import com.ahmetbozkan.bgnmobi.domain.model.GetPokemonsEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokemonListViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_list

    override val viewModel: PokemonListViewModel by viewModels()

    @Inject
    lateinit var pokemonsAdapter: PokemonListAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        subscribeToViewModel()

        initPokemonsRcv()

    }

    private fun subscribeToViewModel() {
        viewModel.pokemons.observe(viewLifecycleOwner, ::observePokemons)
    }

    private fun observePokemons(getPokemonsEntity: GetPokemonsEntity) {
        pokemonsAdapter.submitList(getPokemonsEntity.pokemons)
    }

    private fun initPokemonsRcv() {
        with(binding.rcvPokemons) {
            hasFixedSize()
            adapter = pokemonsAdapter
        }

        pokemonsAdapter.click = object : (Int) -> Unit {
            override fun invoke(position: Int) {
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonDetailsFragment(position + 1)
                navigate(action)
            }
        }
    }


}