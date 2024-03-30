package com.kapozzz.list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kapozzz.list.presentation.MainScreenEvent
import com.kapozzz.list.presentation.MainScreenState
import com.kapozzz.presentation.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    state: MainScreenState,
    setEvent: (event: MainScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier.height(80.dp),
        title = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 22.dp,
                        ),
                    text = stringResource(R.string.catalog),
                    style = AppTypo.titleLarge,
                    color = AppTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
                SortTypeFilter(
                    modifier = Modifier,
                    type = state.searchQuery.collectAsState().value.sortType,
                    onSortTypeChanged = {
                        state.searchQuery.value = state.searchQuery.value.copy(
                            sortType = it
                        )
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.background
        )
    )
}