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

}