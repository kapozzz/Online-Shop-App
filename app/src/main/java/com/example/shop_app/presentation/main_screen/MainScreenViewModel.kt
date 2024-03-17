package com.example.shop_app.presentation.main_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.shop_app.core.common.BaseViewModel
import com.example.shop_app.domain.repositories.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
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
        TODO("Not yet implemented")
    }

    private suspend fun loadItem() {
        val list = withContext(Dispatchers.IO) {
            itemsRepository.getItems()
        }
        Log.d("LIST", "$list")
        currentState.items.value = list
    }
}