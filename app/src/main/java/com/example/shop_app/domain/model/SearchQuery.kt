package com.example.shop_app.domain.model

data class SearchQuery(
    val query: String,
    val tag: String,
    val sortType: SortType,
    val onlyLiked: Boolean
)

sealed class SortType {

    object ByRating: SortType()

    object ByCostAscending: SortType()

    object ByCostDescending: SortType()

}
