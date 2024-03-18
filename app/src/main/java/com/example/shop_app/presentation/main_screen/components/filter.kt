package com.example.shop_app.presentation.main_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.domain.model.SortType
import com.example.shop_app.presentation.theme.ShopAppTheme

@Composable
fun SortTypeFilter(
    onSortTypeChanged: (type: SortType) -> Unit,
    modifier: Modifier = Modifier
) {

    val expanded = remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable { expanded.value = !expanded.value },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 4.dp),
                painter = painterResource(id = R.drawable.sort_type_icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )

            Text(
                modifier = Modifier
                    .padding(end = 4.dp),
                text = stringResource(R.string.sorting),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Icon(
                modifier = Modifier
                    .size(18.dp),
                imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )


        }

        AnimatedVisibility(
            visible = expanded.value,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Column {
                SortTypeItem(type = SortType.ByCostAscending, onClick = onSortTypeChanged)
                SortTypeItem(type = SortType.ByCostDescending, onClick = onSortTypeChanged)
                SortTypeItem(type = SortType.ByRating, onClick = onSortTypeChanged)

            }
        }
    }
}

@Composable
private fun SortTypeItem(
    type: SortType,
    onClick: (type: SortType) -> Unit
) {

    val name = when (type) {
        SortType.ByCostAscending -> "ascending"
        SortType.ByCostDescending -> "descending"
        SortType.ByRating -> "rating"
    }

    Text(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick(type) },
        text = name,
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
@Preview
private fun FilterPreview() {
    ShopAppTheme {
        SortTypeFilter(onSortTypeChanged = {})
    }
}