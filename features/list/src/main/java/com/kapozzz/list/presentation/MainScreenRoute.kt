package com.kapozzz.list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.list.presentation.components.EmptyListScreen
import com.kapozzz.list.presentation.components.ItemsScreen
import com.kapozzz.list.presentation.components.MainScreenTopBar
import com.kapozzz.list.presentation.components.NoInternetScreen
import com.kapozzz.ui.ShopAppTheme
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MainScreenRoute(
    state: MainScreenState,
    effects: SharedFlow<MainScreenEffect>,
    setEvent: (event: MainScreenEvent) -> Unit
) {

    val navigator = LocalNavigator.current
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effects.collect {
                handleEffect(it, navigator)
            }
        }
    }

    MainScreen(
        state = state,
        setEvent = setEvent
    )

}

@Composable
private fun MainScreen(
    state: MainScreenState,
    setEvent: (event: MainScreenEvent) -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            MainScreenTopBar(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp

                    ),
                state = state,
                setEvent = setEvent
            )
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                !state.networkStatus.value -> {
                    NoInternetScreen()
                }
                state.items.value.isEmpty() -> {
                    EmptyListScreen()
                }
                else -> {
                    ItemsScreen(
                        state = state,
                        setEvent = setEvent
                    )
                }
            }
        }
    }
}

private fun handleEffect(effect: MainScreenEffect, navigator: Navigator) {
    when (effect) {
        is MainScreenEffect.OnItemClick -> {
            navigator.navigateToItemDetailScreen(effect.itemID)
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    ShopAppTheme {
        MainScreen(state = MainScreenState(), {})
    }
}