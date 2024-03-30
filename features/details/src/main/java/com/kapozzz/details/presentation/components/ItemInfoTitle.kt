package com.kapozzz.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kapozzz.domain.model.Info
import com.kapozzz.domain.model.UiItem
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo

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
            style = AppTypo.bodyMedium,
            color = AppTheme.colors.onBackground
        )
        Text(
            text = info.value,
            style = AppTypo.bodyMedium,
            color = AppTheme.colors.onBackground
        )
    }
}

@Composable
@Preview
private fun ItemInfoTitlePreview() {
    ItemInfoTitle(
        info = UiItem.getEmptyItem().info.first()
    )
}