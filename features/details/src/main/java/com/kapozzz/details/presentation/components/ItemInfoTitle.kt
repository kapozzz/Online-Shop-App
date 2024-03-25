package com.kapozzz.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kapozzz.domain.model.Info
import com.kapozzz.ui.ShopAppType

@Composable
fun ItemInfoTitle(
    info: Info,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = info.title,
            style = ShopAppType.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = info.value,
            style = ShopAppType.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

//@Composable
//@Preview
//private fun ItemInfoTitlePreview() {
//    ItemInfoTitle(
//        info = fakeItem.info.first()
//    )
//}