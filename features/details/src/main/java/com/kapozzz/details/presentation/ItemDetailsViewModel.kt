package com.kapozzz.details.presentation

import androidx.lifecycle.viewModelScope
import com.kapozzz.common.common.BaseViewModel
import com.kapozzz.domain.repositories.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : BaseViewModel<ItemDetailsEvent, ItemDetailsState, ItemDetailsEffect>() {

    fun setItemID(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.getItemById(id)
                .collect {
                    withContext(Dispatchers.Main) {
                        currentState.item.value = it
                    }
                }
        }
    }

    override fun createInitialState(): ItemDetailsState {
        return ItemDetailsState()
    }

    override fun handleEvent(event: ItemDetailsEvent) {
        when (event) {

            ItemDetailsEvent.OnAddClick -> {
                onAddClick()
            }

        }
    }

    private fun onAddClick() {
        viewModelScope.launch(Dispatchers.IO) {
            currentState.item.value.copy(
                inBasket = !currentState.item.value.inBasket
            ).let {
                itemsRepository.updateItem(
                    it
                )
            }
        }
    }
}