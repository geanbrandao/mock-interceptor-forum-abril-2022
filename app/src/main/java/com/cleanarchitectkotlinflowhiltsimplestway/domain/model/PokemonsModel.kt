package com.cleanarchitectkotlinflowhiltsimplestway.domain.model

data class PokemonsModel(
    val count: Int,
    val results: List<PokemonSimple>
)

data class PokemonSimple(
    val name: String,
    val url: String,
)

data class PokemonFullModel(
    val id: Int,
    val name: String,
    val types: List<Type>
)

data class Type(
    val slot: Int,
    val type: TypeDetail,
)

data class TypeDetail(val name: String)