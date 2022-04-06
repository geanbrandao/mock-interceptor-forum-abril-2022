package com.cleanarchitectkotlinflowhiltsimplestway.presentation.pokemon

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.ActivityPokemonBinding
import com.cleanarchitectkotlinflowhiltsimplestway.domain.model.PokemonFullModel
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    private val binding: ActivityPokemonBinding by lazy {
        ActivityPokemonBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btTryAgain.setOnClickListener {
            getPokemonInfo(1)
        }
        getPokemonInfo(1)
    }

    private fun getPokemonInfo(id: Int) {
        lifecycleScope.launch {
            viewModel.fetchData(id).collect {
                when (it) {
                    is State.DataState -> handleSuccess(it.data)
                    is State.ErrorState -> handleError(it.exception)
                    is State.LoadingState -> handleLoading(it.isLoading)
                }
            }
        }
    }

    private fun handleSuccess(data: PokemonFullModel) {
        binding.clContent.isVisible = true
        binding.tvName.text = "${data.name} - ${data.id}"
        binding.tvTypes.text = if (data.types.size == 2) {
            data.types[0].type.name + "/" + data.types[1].type.name
        } else {
            data.types[0].type.name
        }
    }

    private fun handleError(exception: Throwable) {
        binding.clContent.isVisible = false
        binding.btTryAgain.isVisible = true
        binding.tvText.text = exception.message
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.clContent.isVisible = isLoading.not()
        binding.pbLoading.isVisible = isLoading
    }
}