package com.example.shop_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop_app.domain.navigation.Navigator
import com.example.shop_app.domain.navigation.Routes
import com.example.shop_app.presentation.signIn.SignInScreen
import com.example.shop_app.presentation.signIn.SignInViewModel
import com.example.shop_app.presentation.theme.ShopappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopappTheme {

                val navController = rememberNavController()
                val LocalNavigator = compositionLocalOf { Navigator(navController) }

                CompositionLocalProvider(LocalNavigator provides LocalNavigator.current) {

                    NavHost(navController = navController, startDestination = Routes.SIGN_IN_SCREEN) {

                        composable("initial_screen") {

                            val signInViewModel: SignInViewModel = hiltViewModel()

                            SignInScreen(
                                state = signInViewModel.currentState,
                                effects = signInViewModel.effect,
                                setEvent = signInViewModel::setEvent
                            )
                        }

                    }

                }
            }
        }
    }
}

