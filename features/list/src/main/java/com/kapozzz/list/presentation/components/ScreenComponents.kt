package com.kapozzz.list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kapozzz.list.presentation.MainScreenEvent
import com.kapozzz.list.presentation.MainScreenState
import com.kapozzz.presentation.ItemCard
import com.kapozzz.presentation.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo


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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            FilterPanel(
                state = state,
                modifier = Modifier
                    .padding(
                        vertical = 4.dp,
                        horizontal = 16.dp
                    )
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures { change, dragAmount ->
                            state.panelIsVisible.value = dragAmount.toInt() <= 0
                        }
                    },
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

@Composable
fun Error503() {
    Box(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = AppTheme.colors.error
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 2.dp),
                text = "Failed to connect the server, mock data is used.",
                style = AppTypo.error,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

    }

}