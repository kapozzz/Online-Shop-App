package com.example.shop_app.presentation.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.core.common.UiEffect
import com.example.shop_app.core.common.UiEvent
import com.example.shop_app.core.common.UiState
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.model.SortType

data class MainScreenState(
    val items: MutableState<List<Item>> = mutableStateOf(emptyList()),
    val searchQuery: MutableState<SearchQuery> = mutableStateOf(SearchQuery.getDefault()),
    val loading: MutableState<Boolean> = mutableStateOf(false)
): UiState

sealed class MainScreenEvent: UiEvent {

    data class LikeItem(val id: String): MainScreenEvent()
    data class AddItem(val id: String): MainScreenEvent()
    data class NewSortType(val type: SortType): MainScreenEvent()
    data class OnItemClick(val id: String): MainScreenEvent()

}

sealed class MainScreenEffect: UiEffect {

    data class OnItemClick(val id: String): MainScreenEffect()

}