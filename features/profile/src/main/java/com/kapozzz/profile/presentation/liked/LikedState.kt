package com.kapozzz.profile.presentation.liked

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState
import com.kapozzz.domain.model.UiItem

data class LikedState(
    val items: MutableState<List<UiItem>> = mutableStateOf(emptyList())
): UiState

sealed class LikedEvent: UiEvent {
    data class OnLikeClick(val item: UiItem): LikedEvent()
    data class OnBasketClick(val item: UiItem): LikedEvent()
    data class OnItemClick(val item: UiItem): LikedEvent()
    data object OnBackClick: LikedEvent()
}

sealed class LikedEffect: UiEffect {

    data class OnItemClick(val id: String): LikedEffect()
    data object OnBackClick: LikedEffect()

}