package com.kapozzz.entry.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kapozzz.common.LocalBottomBarState
import com.kapozzz.common.LocalNavigator
import com.kapozzz.presentation.R
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.entry.presentation.components.SignInTextField
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SignInRoute(
    state: SignInState,
    effects: SharedFlow<SignInEffect>,
    setEvent: (SignInEvent) -> Unit
) {

    val navigator = LocalNavigator.current
    val lifecycle = LocalLifecycleOwner.current
    LocalBottomBarState.current.isVisible.value = false

    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effects.collect {
                handleEffect(it, navigator)
            }
        }
    }

    SignInScreen(state = state, setEvent = setEvent)

}

@Composable
private fun SignInScreen(
    state: SignInState,
    setEvent: (event: SignInEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 56.dp
                    ),
                text = stringResource(R.string.registration),
                style = AppTypo.titleLarge,
                textAlign = TextAlign.Start,
                color = AppTheme.colors.onBackground
            )

            SignInTextField(
                value = state.name.value,
                onValueChange = { value ->
                    state.name.value = value
                },
                name = stringResource(R.string.name)
            )

            SignInTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.surname.value,
                onValueChange = { value ->
                    state.surname.value = value
                },
                name = stringResource(R.string.surname)
            )

            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (state.name.value.isNotEmpty() &&
                            state.surname.value.isNotEmpty()
                        ) AppTheme.colors.primary
                        else AppTheme.colors.secondary
                    )
                    .border(
                        color = AppTheme.colors.outline,
                        width = 1.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        if (state.name.value.isNotEmpty() &&
                            state.surname.value.isNotEmpty()
                        ) setEvent(SignInEvent.ValidateUserData)
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(R.string.enter),
                    color = if (state.name.value.isNotEmpty() &&
                        state.surname.value.isNotEmpty()
                    ) AppTheme.colors.onPrimary
                    else AppTheme.colors.onPrimary,
                    style = AppTypo.bodySmall
                )
            }
        }
    }
}


private fun handleEffect(effect: SignInEffect, navigator: Navigator) {
    when (effect) {
        SignInEffect.UserDataIsValidated -> {
            navigator.navigateToMainScreen()
        }
    }
}

@Composable
@Preview
private fun SignInScreenPreview() {
    AppTheme {
        SignInScreen(
            state = SignInState(),
            setEvent = {}
        )
    }
}