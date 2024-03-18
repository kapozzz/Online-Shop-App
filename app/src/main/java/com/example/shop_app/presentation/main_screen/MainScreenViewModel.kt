package com.example.shop_app.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.example.shop_app.core.common.BaseViewModel
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.use_cases.MainScreenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCases: MainScreenUseCases
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

    override fun createInitialState(): MainScreenState {
        return MainScreenState()
    }

    init {
        viewModelScope.launch {
            loadItem()
        }
    }

    override fun handleEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is MainScreenEvent.AddItem -> TODO()
                is MainScreenEvent.LikeItem -> TODO()
                is MainScreenEvent.NewSortType -> {
                    currentState.searchQuery.value = currentState.searchQuery.value.copy(
                        sortType = event.type
                    )
                    updateItemList()
                }
                is MainScreenEvent.OnItemClick -> TODO()
            }
        }
    }

    private suspend fun updateItemList() {
        val list = withContext(Dispatchers.IO) {
            useCases.getItemsUseCase(currentState.searchQuery.value)
        }
        currentState.items.value = list
    }

    private suspend fun loadItem() {
        val list = withContext(Dispatchers.IO) {
            useCases.getItemsUseCase(SearchQuery.getDefault())
        }
        currentState.items.value = list
    }
}