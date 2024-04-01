package com.kapozzz.profile.presentation.support

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState

data class SupportState(
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
): UiState

sealed class SupportEvent: UiEvent {
    data object OnBackClick: SupportEvent()
}

sealed class SupportEffect: UiEffect {
    data object OnBackClick: SupportEffect()
}