package com.kapozzz.profile.presentation.liked

import androidx.lifecycle.viewModelScope
import com.kapozzz.common.common.BaseViewModel
import com.kapozzz.domain.model.UiItem
import com.kapozzz.domain.repositories.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : BaseViewModel<LikedEvent, LikedState, LikedEffect>() {
    override fun createInitialState(): LikedState {
        return LikedState()
    }

    init {
        initialize()
    }

    override fun handleEvent(event: LikedEvent) {
        when (event) {
            is LikedEvent.OnBasketClick -> onBasketClick(event.item)
            is LikedEvent.OnItemClick -> onItemClick(event.item.id)
            is LikedEvent.OnLikeClick -> onLikeClick(event.item)
            is LikedEvent.OnBackClick -> onBackClick()
        }
    }

    private fun initialize() {
        viewModelScope.launch {
            itemsRepository.getLikedItems()
                .collect {
                    currentState.items.value = it
                }
        }
    }

    private fun onItemClick(id: String) {
        setEffect(LikedEffect.OnItemClick(id))
    }

    private fun onLikeClick(item: UiItem) {
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.updateItem(
                item.copy(
                    isLiked = !item.isLiked
                )
            )
        }
    }

    private fun onBasketClick(item: UiItem) {
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.updateItem(
                item.copy(
                    inBasket = !item.inBasket
                )
            )
        }
    }

    private fun onBackClick() {
        setEffect(LikedEffect.OnBackClick)
    }

}