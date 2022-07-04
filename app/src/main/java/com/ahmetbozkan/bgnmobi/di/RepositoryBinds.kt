package com.ahmetbozkan.bgnmobi.di

import com.ahmetbozkan.bgnmobi.data.remote.repository.PokeRepositoryImpl
import com.ahmetbozkan.bgnmobi.domain.repository.PokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBinds {

    @Binds
    @ViewModelScoped
    abstract fun bindPokeRepository(pokeRepositoryImpl: PokeRepositoryImpl): PokeRepository

}