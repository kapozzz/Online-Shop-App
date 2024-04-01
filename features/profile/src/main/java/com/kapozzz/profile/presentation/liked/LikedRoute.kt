package com.kapozzz.profile.presentation.liked

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.presentation.ItemCard
import com.kapozzz.profile.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun LikedRoute(
    state: LikedState,
    effect: SharedFlow<LikedEffect>,
    setEvent: (event: LikedEvent) -> Unit
) {

    val navigator = LocalNavigator.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effect.collect {
                handleEffect(it, navigator)
            }
        }
    }

    LikedScreen(
        state = state,
        setEvent = setEvent
    )
}

private fun handleEffect(effect: LikedEffect, navigator: Navigator) {
    when (effect) {
        is LikedEffect.OnBackClick -> navigator.back()
        is LikedEffect.OnItemClick -> navigator.navigateToItemDetailScreen(effect.id)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LikedScreen(
    state: LikedState,
    setEvent: (event: LikedEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.liked_items),
                        style = AppTypo.titleLarge,
                        color = AppTheme.colors.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { setEvent(LikedEvent.OnBackClick) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = AppTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
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
                            setEvent(LikedEvent.OnBasketClick(it))
                        },
                        onLikeClick = {
                            setEvent(LikedEvent.OnLikeClick(it))
                        },
                        onItemClick = {
                            setEvent(LikedEvent.OnItemClick(it))
                        }
                    )
                }
            }
        }
    }
}