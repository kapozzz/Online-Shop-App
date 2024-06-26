package com.kapozzz.list.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.kapozzz.common.common.BaseViewModel
import com.example.shop_app.domain.util.Resource
import com.google.gson.Gson
import com.kapozzz.common.connectivity.ConnectivityObserver
import com.kapozzz.common.connectivity.NetworkConnectivityObserver
import com.kapozzz.domain.model.UiItem
import com.kapozzz.domain.repositories.ItemsRepository
import com.kapozzz.domain.repositories.RefreshDataRepository
import com.kapozzz.domain.use_cases.MainScreenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCases: MainScreenUseCases,
    private val connectivityObserver: NetworkConnectivityObserver,
    private val refreshDataRepository: RefreshDataRepository,
    private val repository: ItemsRepository
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

    private var job: Job? = null

    override fun createInitialState(): MainScreenState {
        val item = Gson().toJson(UiItem.getEmptyItem())
        Log.i("Class", "$item")
        return MainScreenState()
    }

    init {
        viewModelScope.launch {
            handleNetworkStatus()
        }
        viewModelScope.launch {
            refreshData()
            loadData()
        }

    }

    override fun handleEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when (event) {

                is MainScreenEvent.RefreshData -> {
                    refreshData()
                }

                is MainScreenEvent.LikeItem -> {
                    likeItem(event.item)
                }

                is MainScreenEvent.OnItemClick -> {
                    setEffect(MainScreenEffect.OnItemClick(event.item.id))
                }

                is MainScreenEvent.OnBasketClick -> {
                    onBasketClick(event.item)
                }

            }
        }
    }

    private suspend fun onBasketClick(item: UiItem) {
        withContext(Dispatchers.IO) {
            repository.updateItem(
                item.copy(
                    inBasket = !item.inBasket
                )
            )
        }
    }

    private suspend fun likeItem(item: UiItem) {
        withContext(Dispatchers.IO) {
            repository.updateItem(
                item.copy(
                    isLiked = !item.isLiked
                )
            )
        }
    }

    private suspend fun handleNetworkStatus() {
        connectivityObserver.observe().collect { status ->
            when (status) {
                ConnectivityObserver.Status.Available -> {
                    currentState.networkStatus.value = true
                }

                else -> {
                    currentState.networkStatus.value = false
                }
            }
        }
    }

    private fun refreshData() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            when (connectivityObserver.observe().first()) {
                ConnectivityObserver.Status.Available -> {
                    refreshDataRepository.refresh()
                        .flowOn(Dispatchers.IO)
                        .onEach {

                            when (it) {
                                is Resource.Failure -> {
                                    setEffect(MainScreenEffect.ShowInDialog(it.message.toString()))
                                    currentState.serverError.value = true
                                    currentState.loading.value = false
                                }
                                is Resource.Loading -> currentState.loading.value = true
                                is Resource.Success -> currentState.loading.value = false
                            }

                        }.launchIn(viewModelScope)
                }

                else -> {

                }
            }
        }
    }

    private suspend fun loadData() {
        repository.getItems()
            .flowOn(Dispatchers.IO)
            .combine(currentState.searchQuery) { list, query ->
                useCases.sortItemsUseCase(list, query)
            }
            .collect {
                currentState.items.value = it
            }
    }
}