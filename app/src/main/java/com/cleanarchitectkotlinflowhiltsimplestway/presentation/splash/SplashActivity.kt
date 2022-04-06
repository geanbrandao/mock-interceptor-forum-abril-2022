package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.ActivitySplashBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.pokemon.PokemonActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashActivityViewModel by viewModels()

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getPokemonList()
        binding.btTryAgain.setOnClickListener {
            binding.btTryAgain.isVisible = false
            getPokemonList()
        }

        binding.btNext.setOnClickListener {
            startActivity(Intent(this, PokemonActivity::class.java))
        }
    }

    private fun getPokemonList() {
        lifecycleScope.launch {
            viewModel.fetchData().collect {
                when (it) {
                    is State.DataState -> handleSuccess(it.data.count)
                    is State.ErrorState -> handleError(it.exception)
                    is State.LoadingState -> handleLoading(it.isLoading)
                }
            }
        }
    }

    private fun handleSuccess(count: Int) {
        binding.tvText.text = "O número de pokemons encontrados é $count"
        binding.btNext.isVisible = true
    }

    private fun handleError(exception: Throwable) {
        binding.btTryAgain.isVisible = true
        binding.tvText.text = exception.message
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.tvText.isVisible = isLoading.not()
        binding.pbLoading.isVisible = isLoading
    }
}