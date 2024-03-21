package com.example.shop_app.domain.use_cases

import com.example.shop_app.data.remote.dto.ItemsService
import com.example.shop_app.data.remote.dto.toItems
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.model.SortType
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SortItemsUseCase @Inject constructor() {
    suspend operator fun invoke(list: List<Item>, searchQuery: SearchQuery): List<Item> {
        return getItems(list, searchQuery)
    }

    private suspend fun getItems(list: List<Item>, searchQuery: SearchQuery): List<Item> {
        return when (searchQuery.sortType) {
            SortType.ByCostAscending -> {
                list.sortedBy { it.price.price.toLong() }
            }

            SortType.ByCostDescending -> {
                list.sortedByDescending { it.price.price.toLong() }
            }

            SortType.ByRating -> {
                list.sortedBy { it.feedback.rating }
            }
        }
    }
}


