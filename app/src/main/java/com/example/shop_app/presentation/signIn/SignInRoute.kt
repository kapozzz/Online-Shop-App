package com.example.shop_app.presentation.signIn

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop_app.R
import com.example.shop_app.core.navigation.LocalNavigator
import com.example.shop_app.core.navigation.Navigator
import com.example.shop_app.presentation.signIn.components.SignInTextField
import com.example.shop_app.presentation.theme.ShopAppTheme
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SignInRoute(
    state: SignInState,
    effects: SharedFlow<SignInEffect>,
    setEvent: (SignInEvent) -> Unit
) {

    val navigator = LocalNavigator.current

    LaunchedEffect(true) {
        effects.collect {
            handleEffect(it, navigator)
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
            .background(MaterialTheme.colorScheme.background)
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
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground
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
                        ) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondaryContainer
                    )
                    .border(
                        color = MaterialTheme.colorScheme.outline,
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
                    ) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodySmall
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

    ShopAppTheme {
        SignInScreen(
            state = SignInState(),
            setEvent = {}
        )
    }

}