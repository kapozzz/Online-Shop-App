package com.kapozzz.common

import androidx.compose.runtime.staticCompositionLocalOf
import com.kapozzz.common.navigation.Navigator

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("navigator not found")
}