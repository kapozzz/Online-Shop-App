package com.kapozzz.entry.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo

@Composable
fun SignInTextField(
    value: String,
    onValueChange: (value: String) -> Unit,
    name: String,
    modifier: Modifier = Modifier
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colors.container,
            unfocusedContainerColor = AppTheme.colors.container,
            errorContainerColor = AppTheme.colors.container,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = AppTheme.colors.onContainer,
            unfocusedLabelColor = AppTheme.colors.onContainer,
        ),
        placeholder = {
            Text(
                text = name,
                style = AppTypo.bodySmall,
                color = AppTheme.colors.onContainer
            )
        },
        trailingIcon = {
            if (value != "") {
                IconButton(onClick = {
                    onValueChange("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = AppTheme.colors.onContainer
                    )
                }
            }
        }
    )
}

@Composable
@Preview
private fun SignInTextFieldPreview() {
    AppTheme {
        SignInTextField(
            value = "",
            onValueChange = {},
            name = ""
        )
    }
}