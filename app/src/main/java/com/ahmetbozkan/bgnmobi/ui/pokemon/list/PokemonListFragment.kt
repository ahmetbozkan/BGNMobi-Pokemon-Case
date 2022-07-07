package com.ahmetbozkan.bgnmobi.ui.pokemon.list

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.base.BaseFragment
import com.ahmetbozkan.bgnmobi.databinding.FragmentPokemonListBinding
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity
import com.ahmetbozkan.bgnmobi.util.extensions.orGeneralException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragmentPokemonListBinding, PokemonListViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_list

    override val viewModel: PokemonListViewModel by viewModels()

    @Inject
    lateinit var pokemonsPagingAdapter: PokemonListPagingAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        subscribeToViewModel()

        initPokemonsRcv()

    }

    private fun subscribeToViewModel() {
        viewModel.pokemons.observe(viewLifecycleOwner, ::observePokemons)
    }

    private fun observePokemons(pagingData: PagingData<PokemonEntity>) {
        pokemonsPagingAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        binding.root.onSuccess()
    }

    private fun initPokemonsRcv() {
        with(binding.rcvPokemons) {
            hasFixedSize()
            adapter = pokemonsPagingAdapter.withLoadStateHeaderAndFooter(
                header = PokemonLoadStateAdapter { pokemonsPagingAdapter.retry() },
                footer = PokemonLoadStateAdapter { pokemonsPagingAdapter.retry() }
            )

            pokemonsPagingAdapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> binding.root.onLoading()
                    is LoadState.NotLoading -> binding.root.onSuccess()
                    else -> {
                        val error = when {
                            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                            else -> null
                        }
                        error?.let {
                            binding.root.onError(it.error.message.orGeneralException(requireContext())) {
                                pokemonsPagingAdapter.retry()
                            }
                        }
                    }
                }
            }
        }

        pokemonsPagingAdapter.click = object : (Int) -> Unit {
            override fun invoke(position: Int) {
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonDetailsFragment(position + 1)
                navigate(action)
            }
        }
    }


}