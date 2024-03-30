package com.kapozzz.list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kapozzz.domain.model.UiItem
import com.kapozzz.presentation.ImageLoader
import com.kapozzz.presentation.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo

@Composable
fun ItemCard(
    item: UiItem,
    onBasketClick: (item: UiItem) -> Unit,
    onLikeClick: (item: UiItem) -> Unit,
    onItemClick: (item: UiItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(286.dp)
            .width(168.dp)
            .border(
                width = 1.dp,
                color = AppTheme.colors.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onItemClick(item)
            },
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.container)
            ) {

                ImageLoader(
                    model = item.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(144.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 2.dp,
                            start = 6.dp
                        )
                ) {
                    Text(
                        text = item.price.price,
                        color = AppTheme.colors.outline,
                        style = AppTypo.itemMedium
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${item.price.priceWithDiscount} ${item.price.unit}",
                            style = AppTypo.itemLarge,
                            color = AppTheme.colors.onContainer
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(34.dp)
                                .height(16.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(AppTheme.colors.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "-${item.price.discount}%",
                                style = AppTypo.itemSmall,
                                color = AppTheme.colors.onPrimary
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = item.title,
                    style = AppTypo.itemLarge,
                    color = AppTheme.colors.onContainer
                )

                Row(
                    modifier = Modifier
                        .padding(
                            start = 6.dp,
                            top = 60.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.rating_icon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "${item.feedback.rating}",
                        style = AppTypo.itemMedium,
                        color = AppTheme.colors.outline
                    )

                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "(${item.feedback.count})",
                        style = AppTypo.itemMedium,
                        color = AppTheme.colors.outline
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp))
                    .background(AppTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { onBasketClick(item) }) {
                    Icon(
                        imageVector = if (!item.inBasket) Icons.Default.Add
                        else Icons.Default.Close,
                        contentDescription = null,
                        tint = AppTheme.colors.onPrimary
                    )
                }
            }

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onLikeClick(item) }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (item.isLiked) R.drawable.liked_icon
                        else R.drawable.like_icon
                    ),
                    contentDescription = null,
                    tint = AppTheme.colors.primary,
                )
            }
        }
    }
}

@Composable
@Preview
private fun ItemCardPreview() {
    AppTheme {
        ItemCard(
            item = UiItem.getEmptyItem(),
            onBasketClick = {},
            onItemClick = {},
            onLikeClick = {}
        )
    }
}

