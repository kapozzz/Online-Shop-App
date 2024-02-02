package com.example.shop_app.presentation.signIn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.domain.common.UiEffect
import com.example.shop_app.domain.common.UiEvent
import com.example.shop_app.domain.common.UiState

data class SignInState (
    val name: MutableState<String> = mutableStateOf(""),
    val surname: MutableState<String> = mutableStateOf(""),
    val mobilePhone: MutableState<String> = mutableStateOf(""),
): UiState

sealed class SignInEvent: UiEvent {

    object ValidateUserData: SignInEvent()

}

sealed class SignInEffect: UiEffect {

    object UserDataIsValidated: SignInEffect()

}