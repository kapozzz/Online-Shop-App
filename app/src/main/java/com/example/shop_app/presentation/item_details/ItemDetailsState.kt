package com.example.shop_app.presentation.item_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.core.common.UiEffect
import com.example.shop_app.core.common.UiEvent
import com.example.shop_app.core.common.UiState
import com.example.shop_app.domain.model.Item

data class ItemDetailsState(
    val item: MutableState<Item> = mutableStateOf(Item.getEmptyItem())
) : UiState

sealed class ItemDetailsEvent() : UiEvent {

    data object OnAddClick : ItemDetailsEvent()

}

sealed class ItemDetailsEffect() : UiEffect {

}
