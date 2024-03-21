package com.example.shop_app.presentation.main_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.shop_app.core.common.UiEffect
import com.example.shop_app.core.common.UiEvent
import com.example.shop_app.core.common.UiState
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.model.SortType
import kotlinx.coroutines.flow.MutableStateFlow

data class MainScreenState(
    val items: MutableState<List<Item>> = mutableStateOf(emptyList()),
    val loading: MutableState<Boolean> = mutableStateOf(false),
    val networkStatus: MutableState<Boolean> = mutableStateOf(true),
    val searchQuery: MutableStateFlow<SearchQuery> = MutableStateFlow(SearchQuery.getDefault()),
): UiState

sealed class MainScreenEvent: UiEvent {

    data class LikeItem(val item: Item): MainScreenEvent()
    data class AddItem(val item: Item): MainScreenEvent()
    data class OnItemClick(val item: Item): MainScreenEvent()

//    data class SearchQueryChanged(val searchQuery: SearchQuery): MainScreenEvent()
    data object RefreshData: MainScreenEvent()

}

sealed class MainScreenEffect: UiEffect {

    data class OnItemClick(val item: Item): MainScreenEffect()

}