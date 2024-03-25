package com.kapozzz.common.navigation.api

object Features {
    object Entry {

        const val NESTED_ROUTE = "entry_nested_route"

        const val SCREEN_ROUTE = "entry_screen_route"

    }
    object List {

        const val NESTED_ROUTE = "list_nested_route"

        const val SCREEN_ROUTE = "list_screen_route"

    }
    object Details {

        const val NESTED_ROUTE = "details_nested_route"

        const val SCREEN_ROUTE = "details_screen_route/{itemID}"

        const val SCREEN_ROUTE_WITHOUT_ARGS = "details_screen_route"

        const val ARGS = "/{itemID}"

    }
}

