package com.ahmetbozkan.bgnmobi.data.local.mapper

import com.ahmetbozkan.bgnmobi.data.remote.model.GetPokemonDetailsResponseModel
import com.ahmetbozkan.bgnmobi.domain.model.PokemonDetailEntity
import com.ahmetbozkan.bgnmobi.domain.model.PokemonSpriteEntity
import com.ahmetbozkan.bgnmobi.domain.util.BaseMapper
import com.ahmetbozkan.bgnmobi.util.extensions.orZero
import javax.inject.Inject

class GetPokemonDetailsResponseMapper @Inject constructor() :
    BaseMapper<GetPokemonDetailsResponseModel, PokemonDetailEntity> {

    override fun map(from: GetPokemonDetailsResponseModel): PokemonDetailEntity {
        val sprite = PokemonSpriteEntity(
            backDefault = from.sprites?.backDefault.orEmpty(),
            frontDefault = from.sprites?.frontDefault.orEmpty()
        )

        return PokemonDetailEntity(
            id = from.id.orZero(),
            name = from.name.orEmpty(),
            weight = from.weight.orZero(),
            height = from.height.orZero(),
            sprites = sprite
        )
    }
}