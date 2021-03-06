package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import androidx.lifecycle.ViewModel
import com.cleanarchitectkotlinflowhiltsimplestway.domain.useCases.GetPokemonList
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList
) : ViewModel() {

    fun fetchData() = flow {
        emit(State.LoadingState(isLoading = true))
        delay(1500)
        try {
            emit(State.DataState(getPokemonList()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Utils.resolveError(e))
        } finally {
            emit(State.LoadingState(isLoading = false))
        }
    }
}