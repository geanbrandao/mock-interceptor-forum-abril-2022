package com.cleanarchitectkotlinflowhiltsimplestway.domain.useCases

import com.cleanarchitectkotlinflowhiltsimplestway.data.APIs
import com.cleanarchitectkotlinflowhiltsimplestway.domain.model.PokemonsModel
import javax.inject.Inject

class GetPokemonList @Inject constructor(private val apIs: APIs) {
    suspend operator fun invoke(): PokemonsModel = apIs.getPokemonList()
}