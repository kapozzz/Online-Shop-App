package com.example.shop_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shop_app.core.navigation.CompositionWrapper
import com.example.shop_app.core.navigation.LocalNavigator
import com.example.shop_app.core.navigation.Navigator
import com.example.shop_app.core.navigation.Routes
import com.example.shop_app.domain.repositories.SignInRepository
import com.example.shop_app.domain.repositories.UserRepository
import com.example.shop_app.presentation.main_screen.MainScreenRoute
import com.example.shop_app.presentation.signIn.SignInRoute
import com.example.shop_app.presentation.signIn.SignInViewModel
import com.example.shop_app.presentation.theme.ShopAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShopAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
private fun AppNavigation() {

    val navController = rememberNavController()
    val wrapper = CompositionWrapper(navController)

    NavHost(navController = navController, startDestination = Routes.SIGN_IN_SCREEN) {

        composable(Routes.SIGN_IN_SCREEN) {
            wrapper.Wrap {
                val signInViewModel: SignInViewModel = hiltViewModel()
                SignInRoute(
                    state = signInViewModel.currentState,
                    effects = signInViewModel.effect,
                    setEvent = signInViewModel::setEvent
                )
            }

        }

        composable(Routes.MAIN_SCREEN) {
            MainScreenRoute()
        }

    }
}

