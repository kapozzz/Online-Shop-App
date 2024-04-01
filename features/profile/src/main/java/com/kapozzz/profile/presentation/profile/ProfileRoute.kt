package com.kapozzz.profile.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kapozzz.common.LocalNavigator
import com.kapozzz.common.navigation.Navigator
import com.kapozzz.profile.R
import com.kapozzz.profile.presentation.profile.components.ProfileCard
import com.kapozzz.ui.AppTheme
import com.kapozzz.ui.AppTypo
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ProfileRoute(
    state: ProfileState,
    effect: SharedFlow<ProfileEffect>,
    setEvent: (event: ProfileEvent) -> Unit
) {

    val navigator = LocalNavigator.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(true) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            effect.collect {
                handleEffect(
                    effect = it,
                    navigator = navigator
                )
            }
        }
    }

    ProfileScreen(
        state = state,
        setEvent = setEvent
    )

}

private fun handleEffect(effect: ProfileEffect, navigator: Navigator) {
    when (effect) {
        ProfileEffect.OpenLiked -> navigator.navigateToLikedScreen()
        ProfileEffect.OpenSupport -> TODO()
        ProfileEffect.ExitFromAccount -> navigator.navigateToSignIn()
    }
}

@Composable
private fun ProfileScreen(
    state: ProfileState,
    setEvent: (event: ProfileEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .padding(
                        top = 60.dp,
                        start = 112.dp,
                        end = 112.dp
                    ),
                text = stringResource(R.string.profile),
                style = AppTypo.titleLarge,
                color = AppTheme.colors.onBackground
            )

            // User name
            ProfileCard(
                modifier = Modifier
                    .padding(
                        top = 30.dp
                    ),
                onClick = {
                    setEvent(ProfileEvent.ExitFromAccount)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = AppTheme.colors.onContainer
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            text = "${state.user.value.name} ${state.user.value.surname}",
                            style = AppTypo.titleLarge,
                            color = AppTheme.colors.onContainer
                        )
                    }
                    Icon(
                        painter = painterResource(id = com.kapozzz.presentation.R.drawable.exit_icon),
                        contentDescription = null,
                        tint = AppTheme.colors.outline
                    )
                }
            }


            // Liked button
            ProfileCard(
                modifier = Modifier
                    .padding(
                        top = 24.dp
                    ),
                onClick = {
                    setEvent(ProfileEvent.OpenLiked)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = com.kapozzz.presentation.R.drawable.like_icon),
                            contentDescription = null,
                            tint = AppTheme.colors.primary
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 6.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.liked),
                                style = AppTypo.titleLarge,
                                color = AppTheme.colors.onContainer
                            )
                            Text(
                                text = "${state.liked.value}",
                                style = AppTypo.titleMedium,
                                color = AppTheme.colors.outline
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = AppTheme.colors.outline
                    )
                }
            }

            // Support
            ProfileCard(
                modifier = Modifier
                    .padding(
                        top = 24.dp
                    ),
                onClick = {
                    setEvent(ProfileEvent.OpenSupport)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = com.kapozzz.presentation.R.drawable.support_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            text = stringResource(R.string.support),
                            style = AppTypo.titleLarge,
                            color = AppTheme.colors.onContainer
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = AppTheme.colors.outline
                    )
                }
            }
        }

        ProfileCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 80.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            onClick = { setEvent(ProfileEvent.ExitFromAccount) }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.exit),
                style = AppTypo.titleLarge,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.onContainer
            )
        }
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    ProfileScreen(
        state = ProfileState(),
        setEvent = {}
    )
}