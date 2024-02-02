package com.example.shop_app.presentation.signIn

import com.example.shop_app.domain.common.BaseViewModel
import com.example.shop_app.domain.repositories.SignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
): BaseViewModel<SignInEvent, SignInState, SignInEffect>() {

    override fun createInitialState(): SignInState {
        return SignInState()
    }

    override fun handleEvent(event: SignInEvent) {
        when(event) {

            SignInEvent.ValidateUserData -> {


            }

        }
    }

}