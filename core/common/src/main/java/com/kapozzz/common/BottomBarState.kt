package com.kapozzz.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class BottomBarState(
    var isVisible: MutableState<Boolean> = mutableStateOf(false)
)
