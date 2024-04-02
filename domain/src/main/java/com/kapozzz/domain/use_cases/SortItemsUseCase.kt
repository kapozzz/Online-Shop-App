package com.kapozzz.domain.use_cases

import com.kapozzz.domain.model.UiItem
import com.kapozzz.domain.model.SearchQuery
import com.kapozzz.domain.model.SortType
import javax.inject.Inject

class SortItemsUseCase @Inject constructor() {
    suspend operator fun invoke(list: List<UiItem>, searchQuery: SearchQuery): List<UiItem> {
        return getItems(list, searchQuery)
    }

    private suspend fun getItems(list: List<UiItem>, searchQuery: SearchQuery): List<UiItem> {
        return when (searchQuery.sortType) {
            SortType.ByCostAscending -> {
                list.sortedBy { it.price.price.toLong() }
            }

            SortType.ByCostDescending -> {
                list.sortedByDescending { it.price.price.toLong() }
            }

            SortType.ByRating -> {
                list.sortedByDescending { it.feedback.rating }
            }
        }
    }
}


