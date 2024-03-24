package com.example.shop_app.presentation.core_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.shop_app.R
import com.example.shop_app.presentation.theme.ShopAppType

@Composable
fun ImageLoader(
    model: Any?,
    modifier: Modifier = Modifier
) {

    val isLoading = remember {
        mutableStateOf(true)
    }

    val isError = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = model,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            onLoading = {
                isLoading.value = true
            },
            onSuccess = {
                isLoading.value = false
            },
            onError = {

            }
        )

        when {
            isError.value -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.failed_to_load_image),
                        style = ShopAppType.titleMedium
                    )
                }

            }

            isLoading.value -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@Preview
private fun ImageLoaderPreview() {
    ImageLoader(
        model = null
    )
}
