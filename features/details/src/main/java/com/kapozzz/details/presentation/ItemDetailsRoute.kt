package com.kapozzz.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.details.presentation.components.AddToCart
import com.kapozzz.details.presentation.components.ItemDescription
import com.kapozzz.details.presentation.components.ItemInfoTitle
import com.kapozzz.details.presentation.components.ItemPrice
import com.kapozzz.details.presentation.components.ItemRating
import com.kapozzz.presentation.ImageLoader
import com.kapozzz.presentation.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ItemDetailsRoute(
    state: ItemDetailsState,
    setEvent: (event: ItemDetailsEvent) -> Unit,
    effects: SharedFlow<ItemDetailsEffect>
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

    ItemDetailsScreen(
        state = state,
        setEvent = setEvent
    )
}


@Composable
private fun ItemDetailsScreen(
    state: ItemDetailsState,
    setEvent: (event: ItemDetailsEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                )
        ) {

            item {
                ImageLoader(
                    model = state.item.value.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(368.dp)
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 41.dp),
                    text = state.item.value.title,
                    style = AppTypo.bodyMedium,
                    color = AppTheme.colors.outline
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = state.item.value.subtitle,
                    style = AppTypo.bodyLarge,
                    color = AppTheme.colors.onBackground
                )
            }

            item {
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.available_for_order),
                        style = AppTypo.bodySmall,
                        color = AppTheme.colors.outline
                    )
                    Text(
                        text = state.item.value.available.toString(),
                        style = AppTypo.bodySmall,
                        color = AppTheme.colors.outline
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .background(AppTheme.colors.outline)
                        .padding(top = 10.dp)
                )
            }

            item {
                ItemRating(
                    item = state.item.value,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }

            item {
                ItemPrice(
                    item = state.item.value,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 16.dp)
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    text = stringResource(R.string.description),
                    style = AppTypo.bodyLarge,
                    color = AppTheme.colors.onBackground
                )
            }

            item {
                // TODO кнопка навигации к бренду
            }

            item {
                ItemDescription(
                    item = state.item.value,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 34.dp,
                            bottom = 28.dp
                        ),
                    text = stringResource(R.string.characteristics),
                    style = AppTypo.bodyLarge,
                    color = AppTheme.colors.onBackground
                )
            }

            items(
                state.item.value.info,
                key = {
                    it.title
                }
            ) {
                ItemInfoTitle(
                    info = it
                )
                if (state.item.value.info.last() != it) {
                    Spacer(
                        modifier = Modifier
                            .padding(
                                top = 4.dp,
                                bottom = 10.dp
                            )
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(AppTheme.colors.outline)
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(200.dp)
                )
            }
        }
        AddToCart(
            item = state.item.value,
            onClick = {
                setEvent(ItemDetailsEvent.OnAddClick)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 68.dp
                )
        )
    }
}

private fun handleEffect(effect: ItemDetailsEffect, navigator: Navigator) {
    when (effect) {
        else -> {}
    }
}

@Composable
@Preview
private fun ItemDetailsScreenPreview() {
    ItemDetailsScreen(
        state = ItemDetailsState(),
        setEvent = {}
    )
}

