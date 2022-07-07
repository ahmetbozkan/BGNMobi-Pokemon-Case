package com.ahmetbozkan.bgnmobi.ui.pokemon.detail

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseFragment
import com.ahmetbozkan.bgnmobi.core.Resource
import com.ahmetbozkan.bgnmobi.core.Status
import com.ahmetbozkan.bgnmobi.databinding.FragmentPokemonDetailsBinding
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.service.PokemonOverlayService
import com.ahmetbozkan.bgnmobi.util.Constants
import com.ahmetbozkan.bgnmobi.util.extensions.orGeneralException
import com.ahmetbozkan.bgnmobi.util.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment :
    BaseFragment<FragmentPokemonDetailsBinding, PokemonDetailsViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_details

    override val viewModel: PokemonDetailsViewModel by viewModels()

    private val args: PokemonDetailsFragmentArgs by navArgs()

    private var pokemon: PokemonDetailEntity? = null
    private var pokemonId: Int = 0

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        subscribeToViewModel()

        manageClick()

    }

    private fun getArgs() {
        pokemonId = args.id

        viewModel.getPokemonDetails(pokemonId)
    }

    private fun subscribeToViewModel() {
        viewModel.pokemon.observe(viewLifecycleOwner, ::observePokemonDetails)
    }

    private fun observePokemonDetails(result: Resource<PokemonDetailEntity>) {
        when (result.status) {
            Status.SUCCESS -> {
                val pokemonEntity = result.data!!
                onPokemonRetrieved(pokemonEntity)
            }
            Status.ERROR -> {
                binding.root.onError(result.error?.message.orGeneralException(requireContext())) {
                    viewModel.getPokemonDetails(pokemonId)
                }
            }
            Status.LOADING -> binding.root.onLoading()
        }
    }

    private fun onPokemonRetrieved(pokemonDetail: PokemonDetailEntity) = with(binding) {
        this@PokemonDetailsFragment.pokemon = pokemonDetail

        with(binding) {
            pokemon = pokemonDetail

            btnShowInOverlay.text = getString(
                R.string.format_show_pokemon_in_overlay,
                pokemonDetail.name
            )

            root.onSuccess()
            llPokemonContainer.visible()
        }
    }

    private fun manageClick() = with(binding) {
        btnShowInOverlay.setOnClickListener {
            showInOverlay()
        }
    }

    private fun showInOverlay() {
        startForegroundService(
            requireContext(),
            Intent(requireActivity(), PokemonOverlayService::class.java).apply {
                putExtra(Constants.POKEMON, pokemon)
            }
        )
        requireActivity().finish()
    }
}