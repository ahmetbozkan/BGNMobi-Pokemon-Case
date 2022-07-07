package com.ahmetbozkan.bgnmobi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailEntity(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: PokemonSpriteEntity
): Parcelable

@Parcelize
data class PokemonSpriteEntity(
    val backDefault: String,
    val frontDefault: String
): Parcelable
