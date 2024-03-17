package com.example.shop_app.presentation.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.core.common.UiEffect
import com.example.shop_app.core.common.UiEvent
import com.example.shop_app.core.common.UiState
import com.example.shop_app.domain.model.Item

data class MainScreenState(
    val items: MutableState<List<Item>> = mutableStateOf(emptyList())
): UiState

sealed class MainScreenEvent: UiEvent {

}

sealed class MainScreenEffect: UiEffect {

}