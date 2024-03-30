package com.kapozzz.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kapozzz.domain.model.Feedback
import com.kapozzz.domain.model.UiItem
import com.kapozzz.presentation.R
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo

@Composable
fun ItemRating(
    item: UiItem,
    modifier: Modifier = Modifier
) {
    val secondAfterPoint = (item.feedback.rating * 10) % 10
    val rating =
        (if (secondAfterPoint >= 5.0) item.feedback.rating + 1 else item.feedback.rating).toInt()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier.padding(end = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(5) { step ->

                Icon(
                    painter = painterResource(
                        id = if (rating >= step + 1) R.drawable.rating_icon
                        else R.drawable.rating_not_filled_icon
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

            }
        }
        Text(
            text = "${item.feedback.rating} - ${item.feedback.count}",
            style = AppTypo.bodySmall,
            color = AppTheme.colors.outline
        )
        Text(
            text = stringResource(R.string.reviews),
            style = AppTypo.bodySmall,
            color = AppTheme.colors.outline
        )
    }
}

@Composable
@Preview
private fun ItemRatingPreview() {
    ItemRating(
        item = UiItem.getEmptyItem().copy(
            feedback = Feedback(
                count = 196,
                rating = 3.5
            )
        )
    )
}