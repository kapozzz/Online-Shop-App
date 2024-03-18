package com.example.shop_app.presentation.main_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.presentation.main_screen.MainScreenEvent
import com.example.shop_app.presentation.main_screen.MainScreenState
import com.example.shop_app.presentation.theme.ShopAppType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    state: MainScreenState,
    setEvent: (event: MainScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 4.dp,
                        ),
                    text = stringResource(R.string.catalog),
                    style = ShopAppType.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                SortTypeFilter(
                    modifier = Modifier,
                    type = state.searchQuery.value.sortType,
                    onSortTypeChanged = {
                        setEvent(MainScreenEvent.NewSortType(it))
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}