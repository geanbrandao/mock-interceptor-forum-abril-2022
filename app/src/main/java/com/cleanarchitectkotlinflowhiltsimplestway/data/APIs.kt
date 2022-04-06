package com.cleanarchitectkotlinflowhiltsimplestway.data

import com.cleanarchitectkotlinflowhiltsimplestway.domain.model.PokemonFullModel
import com.cleanarchitectkotlinflowhiltsimplestway.domain.model.PokemonsModel
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

interface APIs {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonsModel

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id: Int
    ): PokemonFullModel
}