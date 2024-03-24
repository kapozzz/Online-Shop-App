package com.example.shop_app.presentation.item_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.domain.model.Item
import com.example.shop_app.presentation.theme.ShopAppTheme
import com.example.shop_app.presentation.theme.ShopAppType
import com.example.shop_app.presentation.util.fakeItem

@Composable
fun AddToCart(
    item: Item,
    onClick: (item: Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .width(342.dp)
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                onClick(item)
            },
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (item.inBasket) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.added_to_cart),
                    style = ShopAppType.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Row(
                    modifier = Modifier.padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${item.price.priceWithDiscount}${item.price.unit}",
                        style = ShopAppType.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    val secondary = MaterialTheme.colorScheme.secondaryContainer
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .drawBehind {
                                this.drawLine(
                                    start = Offset(
                                        x = 0f,
                                        y = this.size.height
                                    ),
                                    end = Offset(
                                        x = this.size.width,
                                        y = 0f
                                    ),
                                    color = secondary,
                                    strokeWidth = 4f
                                )
                            },
                        text = "${item.price.price}${item.price.unit}",
                        style = ShopAppType.bodySmall,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = stringResource(R.string.add_to_cart),
                    style = ShopAppType.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
@Preview
private fun AddToCardPreview() {
    ShopAppTheme {
        AddToCart(
            item = fakeItem,
            onClick = {}
        )
    }
}