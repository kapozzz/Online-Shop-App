package com.example.shop_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shop_app.navigation.NavigationProvider
import com.kapozzz.client.dataStore.SettingsDataStore
import com.kapozzz.ui.AppTheme
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
            AppTheme {
                RootScreen(
                    isFirstStart = isFirstStart,
                    navigationProvider = navigationProvider
                )
            }
        }
    }
}

