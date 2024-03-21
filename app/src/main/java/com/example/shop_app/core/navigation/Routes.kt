package com.example.shop_app.core.navigation

object Routes {

    const val SIGN_IN_SCREEN = "sign_in_screen"

    const val MAIN_SCREEN = "main_screen_route"

    object ItemDetails {

        const val BASE_ROUTE = "item_details_screen_route"

        private const val ARGS_ROUTE = "/{itemID}"

        const val ROUTE = "$BASE_ROUTE$ARGS_ROUTE"

    }


}