package com.kapozzz

import com.kapozzz.domain.repositories.ItemsRepository
import com.example.shop_app.domain.util.Resource
import com.kapozzz.domain.repositories.RefreshDataRepository
import com.kapozzz.remote.ItemsService
import com.kapozzz.remote.dto.toItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class RefreshDataRepositoryImpl @Inject constructor(
    private val itemsRepository: ItemsRepository,
    private val itemsService: ItemsService
) : RefreshDataRepository {
    override fun refresh(): Flow<Resource<Unit>> = flow {
        try {

            emit(Resource.Loading())

            val itemsDto = itemsService.getItems()
            itemsRepository.addItems(itemsDto.toItems())

            emit(Resource.Success(Unit))

        } catch (e: Exception) {

            itemsRepository.addItems(mock_items) // run.mocky.io API больше не работает :(
            emit(Resource.Failure(e.message))

        }
    }
}