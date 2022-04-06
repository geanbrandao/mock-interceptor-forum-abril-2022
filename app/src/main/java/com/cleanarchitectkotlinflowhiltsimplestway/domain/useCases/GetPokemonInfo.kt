package com.cleanarchitectkotlinflowhiltsimplestway.domain.useCases

import com.cleanarchitectkotlinflowhiltsimplestway.data.APIs
import com.cleanarchitectkotlinflowhiltsimplestway.domain.model.PokemonFullModel
import javax.inject.Inject

class GetPokemonInfo @Inject constructor(private val apIs: APIs) {
    suspend operator fun invoke(id: Int): PokemonFullModel = apIs.getPokemonInfo(id)
}