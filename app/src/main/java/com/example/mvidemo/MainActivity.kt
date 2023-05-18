package com.example.mvidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvidemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.state.observe(this, Observer { state ->
            render(state)
        })

        binding.btn.setOnClickListener {
            val newText = "Nouveau texte"
            viewModel.consumeIntent(MyIntent.UpdateText(newText))
        }

        viewModel.consumeIntent(MyIntent.Initial)

    }

    private fun render(state: MyState) {
        when (state) {
            is MyState.Loading -> showLoadingState()
            is MyState.Success -> showSuccessState(state.model)
            is MyState.Error -> showErrorState(state.errorMessage)
        }
    }

    private fun showLoadingState() {
        // Afficher un indicateur de chargement ou effectuer d'autres actions liées à l'état de chargement
        binding.txtView.text = "Chargement en cours..."
    }

    private fun showSuccessState(model: MyModel) {
        // Mettre à jour la vue avec le modèle réussi
        binding.txtView.text = model.text
    }

    private fun showErrorState(errorMessage: String) {
        // Afficher un message d'erreur ou effectuer d'autres actions liées à l'état d'erreur
        binding.txtView.text = errorMessage
    }
}