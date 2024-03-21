package com.example.shop_app.presentation.main_screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.presentation.main_screen.components.EmptyListScreen
import com.example.shop_app.presentation.main_screen.components.ItemCard
import com.example.shop_app.presentation.main_screen.components.ItemsScreen
import com.example.shop_app.presentation.main_screen.components.MainScreenTopBar
import com.example.shop_app.presentation.main_screen.components.NoInternetScreen
import com.example.shop_app.presentation.theme.ShopAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MainScreenRoute(
    state: MainScreenState,
    effect: SharedFlow<MainScreenEffect>,
    setEvent: (event: MainScreenEvent) -> Unit
) {

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

@Composable
@Preview
private fun MainScreenPreview() {
    ShopAppTheme {
        MainScreen(state = MainScreenState(), {})
    }
}