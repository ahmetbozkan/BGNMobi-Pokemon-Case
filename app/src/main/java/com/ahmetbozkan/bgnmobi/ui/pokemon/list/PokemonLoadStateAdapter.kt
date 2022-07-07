package com.ahmetbozkan.bgnmobi.ui.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.databinding.LayoutPokemonListFooterBinding
import com.ahmetbozkan.bgnmobi.util.extensions.gone
import com.ahmetbozkan.bgnmobi.util.extensions.invisible
import com.ahmetbozkan.bgnmobi.util.extensions.visible

class PokemonLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PokemonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_pokemon_list_footer,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: LayoutPokemonListFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                when (loadState) {
                    is LoadState.Loading -> {
                        progressBar.visible()
                        buttonRetry.gone()
                        tvError.gone()
                        imgWarning.gone()
                    }
                    is LoadState.Error -> {
                        progressBar.invisible()
                        buttonRetry.visible()
                        tvError.visible()
                        imgWarning.visible()
                    }
                    is LoadState.NotLoading -> {}
                }

            }
        }

    }


}