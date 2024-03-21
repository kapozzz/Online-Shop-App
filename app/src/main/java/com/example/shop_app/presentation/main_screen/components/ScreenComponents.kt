package com.example.shop_app.presentation.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.presentation.main_screen.MainScreenEvent
import com.example.shop_app.presentation.main_screen.MainScreenState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ItemsScreen(
    state: MainScreenState,
    setEvent: (MainScreenEvent) -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.loading.value)

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize(),
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
                    onBasketClick = {
                        setEvent(MainScreenEvent.OnBasketClick(it))
                    },
                    onLikeClick = {
                        setEvent(MainScreenEvent.LikeItem(it))
                    },
                    onItemClick = {
                        setEvent(MainScreenEvent.OnItemClick(it))
                    }
                )
            }
        }
    }
}

@Composable
fun NoInternetScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_internet_connection)
        )
    }
}

@Composable
fun EmptyListScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_products_found)
        )
    }
}