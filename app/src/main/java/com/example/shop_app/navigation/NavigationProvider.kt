package com.example.shop_app.navigation

import com.kapozzz.details.navigation.DetailsApi
import com.kapozzz.entry.navigation.EntryApi
import com.kapozzz.list.navigation.ListApi

data class NavigationProvider(
    val entry: EntryApi,
    val list: ListApi,
    val details: DetailsApi
)