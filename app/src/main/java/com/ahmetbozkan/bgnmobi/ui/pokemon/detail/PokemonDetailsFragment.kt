package com.ahmetbozkan.bgnmobi.ui.pokemon.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseFragment
import com.ahmetbozkan.bgnmobi.databinding.FragmentPokemonDetailsBinding
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment :
    BaseFragment<FragmentPokemonDetailsBinding, PokemonDetailsViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_details

    override val viewModel: PokemonDetailsViewModel by viewModels()

    private val args: PokemonDetailsFragmentArgs by navArgs()

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        subscribeToViewModel()

    }

    private fun getArgs() {
        val pokemonId = args.id

        viewModel.getPokemonDetails(pokemonId)
    }

    private fun subscribeToViewModel() {
        viewModel.pokemon.observe(viewLifecycleOwner, ::observePokemonDetails)
    }

    private fun observePokemonDetails(pokemonDetailEntity: PokemonDetailEntity) {
        with(binding) {
            pokemon = pokemonDetailEntity

            btnShowInOverlay.text = getString(
                R.string.format_show_pokemon_in_overlay,
                pokemonDetailEntity.name
            )
        }
    }
}