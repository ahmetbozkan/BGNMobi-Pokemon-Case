package com.ahmetbozkan.bgnmobi.ui.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmetbozkan.bgnmobi.R
import com.ahmetbozkan.bgnmobi.databinding.RowPokemonItemBinding
import com.ahmetbozkan.bgnmobi.domain.model.PokemonEntity
import javax.inject.Inject

class PokemonListPagingAdapter @Inject constructor() :
    PagingDataAdapter<PokemonEntity, PokemonListPagingAdapter.PokemonViewHolder>(diffUtil) {

    inner class PokemonViewHolder(val binding: RowPokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    var click: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_pokemon_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = getItem(position)

        with(holder.binding) {
            pokemon = currentItem

            root.setOnClickListener {
                click?.invoke(position)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PokemonEntity>() {
            override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: PokemonEntity,
                newItem: PokemonEntity
            ): Boolean = oldItem == newItem

        }
    }

}