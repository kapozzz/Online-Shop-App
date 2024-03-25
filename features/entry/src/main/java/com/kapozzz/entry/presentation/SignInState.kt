package com.kapozzz.entry.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState

data class SignInState (
    val name: MutableState<String> = mutableStateOf(""),
    val surname: MutableState<String> = mutableStateOf(""),
    val mobilePhone: MutableState<String> = mutableStateOf(""),
    val loading: MutableState<Boolean> = mutableStateOf(false)
): UiState

sealed class SignInEvent: UiEvent {

    data object ValidateUserData: SignInEvent()

}

sealed class SignInEffect: UiEffect {

    data object UserDataIsValidated: SignInEffect()

}