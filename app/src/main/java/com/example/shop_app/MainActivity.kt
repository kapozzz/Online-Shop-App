package com.example.shop_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.shop_app.navigation.AppNavGraph
import com.example.shop_app.navigation.NavigationProvider
import com.kapozzz.client.dataStore.SettingsDataStore
import com.kapozzz.common.navigation.api.Features
import com.kapozzz.ui.ShopAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstStart: Boolean
        runBlocking {
            isFirstStart = settingsDataStore.isFirstStart()
        }

        setContent {
            ShopAppTheme {
                Scaffold(
                    bottomBar = {

                    }
                ) {

                    val startDestination =
                        if (isFirstStart) Features.Entry.NESTED_ROUTE else Features.List.NESTED_ROUTE

                    val navController = rememberNavController()

                    AppNavGraph(
                        navController = navController,
                        navigationProvider = navigationProvider,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}

