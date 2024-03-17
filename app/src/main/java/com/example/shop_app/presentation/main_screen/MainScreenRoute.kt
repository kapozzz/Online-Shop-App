package com.example.shop_app.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shop_app.presentation.main_screen.components.ItemCard
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun MainScreenRoute(
    state: MainScreenState,
    effect: SharedFlow<MainScreenEffect>,
    setEvent: (event: MainScreenEvent) -> Unit
) {

    MainScreen(state = state)

}

@Composable
private fun MainScreen(
    state: MainScreenState
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                text = "Catalog",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            LazyVerticalGrid(
                modifier = Modifier.padding(
                    top = 10.dp
                ),
                contentPadding = PaddingValues(8.dp),
                columns = GridCells.Fixed(2), content = {

                    items(state.items.value) { item ->
                        ItemCard(
                            modifier = Modifier.padding(8.dp),
                            item = item,
                            addItem = { },
                            onLikeClick = {}
                        )
                    }

                })
        }
    }
}