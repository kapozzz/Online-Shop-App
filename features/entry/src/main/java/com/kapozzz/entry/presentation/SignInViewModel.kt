package com.kapozzz.entry.presentation

import androidx.lifecycle.viewModelScope
import com.kapozzz.common.common.BaseViewModel
import com.kapozzz.domain.model.User
import com.kapozzz.domain.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : BaseViewModel<SignInEvent, SignInState, SignInEffect>() {

    override fun createInitialState(): SignInState {
        return SignInState()
    }

    override fun handleEvent(event: SignInEvent) {
        when (event) {
            SignInEvent.ValidateUserData -> {
                validateUser()
            }
        }
    }

    private fun validateUser() {
        val user = User(
            name = currentState.name.value,
            surname = currentState.surname.value,
            mobilePhone = currentState.mobilePhone.value
        )
        viewModelScope.launch {
            signInRepository.saveUser(user)
            setEffect(SignInEffect.UserDataIsValidated)
        }
    }
}