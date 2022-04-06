package com.cleanarchitectkotlinflowhiltsimplestway.presentation.pokemon

import androidx.lifecycle.ViewModel
import com.cleanarchitectkotlinflowhiltsimplestway.domain.useCases.GetPokemonInfo
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonInfo: GetPokemonInfo
) : ViewModel() {

    fun fetchData(id: Int) = flow {
        emit(State.LoadingState(isLoading = true))
        kotlinx.coroutines.delay(1000)
        try {
            emit(State.DataState(getPokemonInfo(id)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Utils.resolveError(e))
        } finally {
            emit(State.LoadingState(isLoading = false))
        }
    }
}