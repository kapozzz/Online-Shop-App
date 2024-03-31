package com.example.shop_app.navigation

import com.kapozzz.cart.navigation.CartApi
import com.kapozzz.details.navigation.DetailsApi
import com.kapozzz.entry.navigation.EntryApi
import com.kapozzz.profile.navigation.ProfileApi
import com.kapozzz.list.navigation.ListApi

data class NavigationProvider(
    val entry: EntryApi,
    val list: ListApi,
    val details: DetailsApi,
    val profile: ProfileApi,
    val cart: CartApi
)