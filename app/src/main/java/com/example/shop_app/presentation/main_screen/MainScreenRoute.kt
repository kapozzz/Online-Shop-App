package com.example.shop_app.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.presentation.main_screen.components.ItemCard
import com.example.shop_app.presentation.main_screen.components.MainScreenTopBar
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
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(80.dp))
    }
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

        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.loading.value)

        SwipeRefresh(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            state = swipeRefreshState,
            onRefresh = { setEvent(MainScreenEvent.RefreshData) }
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                contentPadding = PaddingValues(8.dp),
                columns = GridCells.Fixed(2)
            ) {

                items(
                    state.items.value,
                    key = { item -> item.id }
                ) { item ->
                    ItemCard(
                        modifier = Modifier.padding(8.dp),
                        item = item,
                        addItem = {
                            // todo
                        },
                        onLikeClick = {
                            // todo
                        }
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