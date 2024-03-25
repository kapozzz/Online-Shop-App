package com.kapozzz.list.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.kapozzz.common.common.UiEffect
import com.kapozzz.common.common.UiEvent
import com.kapozzz.common.common.UiState
import com.kapozzz.domain.model.SearchQuery
import com.kapozzz.domain.model.UiItem
import kotlinx.coroutines.flow.MutableStateFlow

data class MainScreenState(
    val items: MutableState<List<UiItem>> = mutableStateOf(emptyList()),
    val loading: MutableState<Boolean> = mutableStateOf(false),
    val networkStatus: MutableState<Boolean> = mutableStateOf(true),
    val searchQuery: MutableStateFlow<SearchQuery> = MutableStateFlow(SearchQuery.getDefault()),
): UiState

sealed class MainScreenEvent: UiEvent {

    data class LikeItem(val item: UiItem): MainScreenEvent()
    data class OnBasketClick(val item: UiItem): MainScreenEvent()
    data class OnItemClick(val item: UiItem): MainScreenEvent()
    data object RefreshData: MainScreenEvent()

}

sealed class MainScreenEffect: UiEffect {

    data class OnItemClick(val itemID: String): MainScreenEffect()

}