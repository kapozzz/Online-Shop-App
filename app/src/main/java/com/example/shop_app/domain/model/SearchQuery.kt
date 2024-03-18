package com.example.shop_app.domain.model

data class SearchQuery(
    val query: String,
    val tag: String,
    val sortType: SortType,
    val onlyLiked: Boolean
) {
    companion object {
        fun getDefault(): SearchQuery {
            return SearchQuery(
                query = "",
                tag = "",
                sortType = SortType.ByRating,
                onlyLiked = false
            )
        }
    }
}

sealed class SortType {

    data object ByRating: SortType()

    data object ByCostAscending: SortType()

    data object ByCostDescending: SortType()

}
