package com.example.shop_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.NavigatorState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shop_app.core.navigation.CompositionWrapper
import com.example.shop_app.core.navigation.LocalNavigator
import com.example.shop_app.core.navigation.Navigator
import com.example.shop_app.core.navigation.Routes
import com.example.shop_app.data.client.dataStore.SettingsDataStore
import com.example.shop_app.domain.repositories.SignInRepository
import com.example.shop_app.domain.repositories.UserRepository
import com.example.shop_app.presentation.item_details.ItemDetailsRoute
import com.example.shop_app.presentation.item_details.ItemDetailsViewModel
import com.example.shop_app.presentation.main_screen.MainScreenRoute
import com.example.shop_app.presentation.main_screen.MainScreenViewModel
import com.example.shop_app.presentation.signIn.SignInRoute
import com.example.shop_app.presentation.signIn.SignInViewModel
import com.example.shop_app.presentation.theme.ShopAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstStart: Boolean
        runBlocking {
            isFirstStart = settingsDataStore.isFirstStart()
        }

        setContent {
            ShopAppTheme {
                AppNavigation(isFirstStart)
            }
        }
    }
}

@Composable
private fun AppNavigation(isFirstStart: Boolean) {

    val startDestination = if (isFirstStart) Routes.SIGN_IN_SCREEN else Routes.MAIN_SCREEN

    val navController = rememberNavController()
    val wrapper = CompositionWrapper(navController)

    NavHost(navController = navController, startDestination = startDestination) {

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
            wrapper.Wrap {
                val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
                MainScreenRoute(
                    state = mainScreenViewModel.currentState,
                    effects = mainScreenViewModel.effect,
                    setEvent = mainScreenViewModel::setEvent
                )
            }
        }


        composable(
            Routes.ItemDetails.ROUTE,
            arguments = listOf(navArgument("itemID") {type = NavType.StringType})
        ) {

            val itemID = it.arguments?.getString("itemID")

            val itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel()

            itemID?.let {
                itemDetailsViewModel.setItemID(itemID)
            } ?: throw IllegalStateException("item id is null")

            wrapper.Wrap {
                ItemDetailsRoute(
                    state = itemDetailsViewModel.currentState,
                    setEvent = itemDetailsViewModel::setEvent,
                    effects = itemDetailsViewModel.effect
                )
            }
        }
    }
}

