package com.example.ch7.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _register = MutableLiveData<Unit>()
    val register: LiveData<Unit> = _register

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    _register.value = registerUseCase.invoke(username, email, password, confirmPassword)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _loading.value = false
                }
            }
        }
    }
}
