package com.example.shop_app.domain.use_cases

import com.example.shop_app.core.common.ConnectivityObserver
import com.example.shop_app.core.common.NetworkConnectivityObserver
import com.example.shop_app.data.remote.dto.ItemsService
import com.example.shop_app.data.remote.dto.toItems
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.SearchQuery
import com.example.shop_app.domain.model.SortType
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
    private val itemsService: ItemsService,
    private val connectivityObserver: NetworkConnectivityObserver
) {

    suspend operator fun invoke(searchQuery: SearchQuery): Flow<Resource<List<Item>>> {

        return flow {
            try {
                emit(Resource.Loading())
                val items = getItems(searchQuery)
                emit(Resource.Success(items))
            } catch (e: Exception) {
                emit(Resource.Failure(e.message))
            }
        }
    }

    private suspend fun getItems(searchQuery: SearchQuery): List<Item> {

        val itemsDto = itemsService.getItems()

        itemsRepository.addItems(itemsDto.toItems())


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


