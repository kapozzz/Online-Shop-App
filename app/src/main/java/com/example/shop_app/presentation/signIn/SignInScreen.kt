package com.example.shop_app.presentation.signIn

import android.media.effect.Effect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SignInScreen(
    state: SignInState,
    effects: SharedFlow<Effect>
) {

    LaunchedEffect(true) {
        effects.collect {

        }
    }


}


private fun handleEffect(effect: Effect) {

}