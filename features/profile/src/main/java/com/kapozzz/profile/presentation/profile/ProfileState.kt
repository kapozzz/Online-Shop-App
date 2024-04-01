package com.kapozzz.profile.presentation.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState
import com.kapozzz.domain.model.User

data class ProfileState(
    val user: MutableState<User> = mutableStateOf(User.getEmpty()),
    val liked: MutableState<Int> = mutableIntStateOf(0)
) : UiState

sealed class ProfileEvent(): UiEvent {
    data object OpenLiked: ProfileEvent()
    data object OpenSupport: ProfileEvent()
    data object ExitFromAccount: ProfileEvent()
}

sealed class ProfileEffect(): UiEffect {
    data object OpenLiked: ProfileEffect()
    data object OpenSupport: ProfileEffect()
    data object ExitFromAccount: ProfileEffect()
}