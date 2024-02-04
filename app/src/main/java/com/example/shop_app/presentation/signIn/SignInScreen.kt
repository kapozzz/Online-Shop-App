package com.example.shop_app.presentation.signIn

import android.media.effect.Effect
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SignInScreen(
    state: SignInState,
    effects: SharedFlow<SignInEffect>,
    setEvent: (SignInEvent) -> Unit
) {

    LaunchedEffect(true) {
        effects.collect {

        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }

}


private fun handleEffect(effect: Effect) {

}