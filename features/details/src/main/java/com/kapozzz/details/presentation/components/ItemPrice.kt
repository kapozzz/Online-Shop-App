package com.kapozzz.details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kapozzz.domain.model.UiItem
import com.kapozzz.ui.ShopAppType

@Composable
fun ItemPrice(
    item: UiItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "${item.price.priceWithDiscount}${item.price.unit}",
            style = ShopAppType.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        val secondary = MaterialTheme.colorScheme.onBackground
        Text(
            modifier = Modifier
                .padding(12.dp)
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
            color = MaterialTheme.colorScheme.secondary
        )
        Box(
            modifier = Modifier
                .padding(start = 11.dp)
                .width(34.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "-${item.price.discount}%",
                style = ShopAppType.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

//@Composable
//@Preview
//private fun ItemPricePreview() {
//    ItemPrice(
//        item = fakeItem
//    )
//}