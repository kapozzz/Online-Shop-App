package com.example.shop_app.domain.use_cases

import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.model.SortType
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke(searchQuery: SearchQuery): List<Item> {

        val items = mutableListOf<Item>()

        items.addAll(if (searchQuery.onlyLiked) itemsRepository.getLikedItems() else itemsRepository.getItems())

        when (searchQuery.sortType) {

            SortType.ByCostAscending -> {
                items.sortBy { it.price.price.toLong() }
            }

            SortType.ByCostDescending -> {
                items.sortByDescending { it.price.price.toLong() }
            }

            SortType.ByRating -> {
                items.sortBy { it.feedback.rating }
            }

        }

        return items

    }
}