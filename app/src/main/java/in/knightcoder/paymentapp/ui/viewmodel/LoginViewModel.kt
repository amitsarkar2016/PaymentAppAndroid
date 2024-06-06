package `in`.knightcoder.paymentapp.ui.viewmodel

/**
 * @Author: Amit Sarkar
 * @Date: 06-06-2024
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.knightcoder.paymentapp.data.model.LoginResponse
import `in`.knightcoder.paymentapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    sealed interface LoginUiState {
        data object Empty : LoginUiState
        data object Loading : LoginUiState
        data class Success(val response: LoginResponse?) : LoginUiState
        data class Error(val message: String) : LoginUiState
    }

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading
            try {
                val response = userRepository.login(username, password)
                if (response?.status == true) {
                    _loginState.value = LoginUiState.Success(response)
                } else {
                    response?.msg?.let {
                        _loginState.value = LoginUiState.Error(it)
                    }
                }
            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}