package com.kapozzz.details.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kapozzz.presentation.R
import com.kapozzz.domain.model.UiItem
import com.kapozzz.ui.ShopAppType

@Composable
fun ItemDescription(
    item: UiItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (item.description.length > 100) {
            val expanded = remember {
                mutableStateOf(false)
            }
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    text = if (expanded.value) item.description else "${item.description.take(100)} ...",
                    style = ShopAppType.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        },
                    text = if (expanded.value) stringResource(R.string.hide)
                    else stringResource(R.string.more),
                    style = ShopAppType.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = item.description,
                style = ShopAppType.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}