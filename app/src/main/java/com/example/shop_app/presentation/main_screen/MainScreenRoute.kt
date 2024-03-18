package com.example.shop_app.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.presentation.main_screen.components.ItemCard
import com.example.shop_app.presentation.main_screen.components.MainScreenTopBar
import com.example.shop_app.presentation.theme.ShopAppTheme
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

@OptIn(ExperimentalMaterial3Api::class)
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
            MainScreenTopBar(setEvent = setEvent)
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {


            LazyVerticalGrid(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    ),
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