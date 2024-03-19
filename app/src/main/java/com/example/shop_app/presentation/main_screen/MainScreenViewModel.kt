package com.example.shop_app.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.example.shop_app.core.common.BaseViewModel
import com.example.shop_app.core.common.ConnectivityObserver
import com.example.shop_app.core.common.NetworkConnectivityObserver
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.use_cases.MainScreenUseCases
import com.example.shop_app.domain.util.Resource
import com.example.shop_app.presentation.signIn.SignInEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCases: MainScreenUseCases,
    private val connectivityObserver: NetworkConnectivityObserver
) : BaseViewModel<MainScreenEvent, MainScreenState, MainScreenEffect>() {

    private var job: Job? = null

    override fun createInitialState(): MainScreenState {
        return MainScreenState()
    }

    init {
        viewModelScope.launch {
            handleNetworkStatus()
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadData()
            }
        }

    }

    override fun handleEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is MainScreenEvent.NewSortType -> {
                    currentState.searchQuery.value = currentState.searchQuery.value.copy(
                        sortType = event.type
                    )
                    withContext(Dispatchers.IO) {
                        loadData(currentState.searchQuery.value)
                    }
                }

                is MainScreenEvent.RefreshData -> {
                    loadData()
                }

                is MainScreenEvent.AddItem -> TODO()
                is MainScreenEvent.LikeItem -> TODO()
                is MainScreenEvent.OnItemClick -> TODO()
            }
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

    private suspend fun loadData(
        searchQuery: SearchQuery = SearchQuery.getDefault()
    ) {

        job?.cancel()

        when (connectivityObserver.observe().first()) {
            ConnectivityObserver.Status.Available -> {
                val itemsFlow = useCases.getItemsUseCase(searchQuery = searchQuery)
                job = itemsFlow
                    .flowOn(Dispatchers.IO)
                    .onEach { resource ->
                        when (resource) {
                            is Resource.Failure -> {
                                throw IOException(resource.message)
                            }

                            is Resource.Success -> {
                                currentState.items.value = resource.data ?: emptyList()
                                currentState.loading.value = false
                            }

                            is Resource.Loading -> {
                                currentState.loading.value = true
                            }
                        }
                    }
                    .launchIn(viewModelScope)
            }

            else -> {
                return
            }
        }
    }
}