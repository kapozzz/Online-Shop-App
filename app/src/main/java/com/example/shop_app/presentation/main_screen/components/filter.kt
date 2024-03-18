package com.example.shop_app.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.domain.model.SortType
import com.example.shop_app.presentation.theme.ShopAppTheme
import com.example.shop_app.presentation.theme.ShopAppType

@Composable
fun SortTypeFilter(
    type: SortType,
    onSortTypeChanged: (type: SortType) -> Unit,
    modifier: Modifier = Modifier
) {

    val expanded = remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(8.dp))
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
                style = ShopAppType.titleMediumBold,
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

        DropdownMenu(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {

            val onClick: (SortType) -> Unit = {
                onSortTypeChanged(it)
                expanded.value = false
            }

            SortTypeItem(
                type = SortType.ByCostAscending,
                onClick = onClick,
                enabled = type is SortType.ByCostAscending
                )
            Spacer(modifier = Modifier.height(4.dp))
            SortTypeItem(
                type = SortType.ByCostDescending,
                onClick = onClick,
                enabled = type is SortType.ByCostDescending
            )
            Spacer(modifier = Modifier.height(4.dp))
            SortTypeItem(
                type = SortType.ByRating,
                onClick = onClick,
                enabled = type is SortType.ByRating
            )

        }
    }
}

@Composable
private fun SortTypeItem(
    type: SortType,
    enabled: Boolean,
    onClick: (type: SortType) -> Unit
) {

    val name = when (type) {
        SortType.ByCostAscending -> stringResource(R.string.ascending)
        SortType.ByCostDescending -> stringResource(R.string.descending)
        SortType.ByRating -> stringResource(R.string.rating)
    }

    Text(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(type) },
        text = name,
        style = if (enabled) ShopAppType.titleMediumBold else ShopAppType.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

//@Composable
//@Preview
//private fun FilterPreview() {
//    ShopAppTheme {
//        SortTypeFilter(onSortTypeChanged = {})
//    }
//}