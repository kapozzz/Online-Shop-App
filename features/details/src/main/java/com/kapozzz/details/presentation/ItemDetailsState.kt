package com.kapozzz.details.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState
import com.kapozzz.domain.model.UiItem

data class ItemDetailsState(
    val item: MutableState<UiItem> = mutableStateOf(UiItem.getEmptyItem())
) : UiState

sealed class ItemDetailsEvent() : UiEvent {

    data object OnAddClick : ItemDetailsEvent()

}

sealed class ItemDetailsEffect() : UiEffect {

}
