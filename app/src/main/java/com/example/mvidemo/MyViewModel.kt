package com.example.mvidemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MyModel(val text: String)

sealed class MyState {
    object Loading : MyState()
    data class Success(val model: MyModel) : MyState()
    data class Error(val errorMessage: String) : MyState()
}

sealed class MyIntent {
    object Initial : MyIntent()
    data class UpdateText(val newText: String) : MyIntent()
}

class MyViewModel : ViewModel() {
    private val _state = MutableLiveData<MyState>()
    val state: LiveData<MyState> get() = _state

    fun consumeIntent(intent: MyIntent) {
        when (intent) {
            is MyIntent.Initial -> {
                // Effectuer des actions initiales (chargement initial des données, etc.)
                _state.value = MyState.Loading
                simulateDataLoading()
            }
            is MyIntent.UpdateText -> {
                // Mettre à jour le modèle avec le nouveau texte
                _state.value = MyState.Success(MyModel(intent.newText))
            }
        }
    }

    private fun simulateDataLoading() {
        // Simuler un chargement asynchrone des données
        // Après le chargement, mettre à jour le modèle avec les données réussies ou une erreur
        val success = (0..1).random() == 0 // Simule une réussite aléatoire
        if (success) {
            val newText = "Données chargées avec succès"
            _state.value = MyState.Success(MyModel(newText))
        } else {
            val errorMessage = "Échec du chargement des données"
            _state.value = MyState.Error(errorMessage)
        }
    }


}