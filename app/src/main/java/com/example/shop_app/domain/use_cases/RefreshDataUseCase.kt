package com.example.shop_app.domain.use_cases

import com.example.shop_app.data.remote.dto.ItemsService
import com.example.shop_app.data.remote.dto.toItems
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshDataUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository,
    private val itemsService: ItemsService
) {
    suspend operator fun invoke(): Flow<Resource<Unit>> = flow {
        try {

            emit(Resource.Loading())

            val itemsDto = itemsService.getItems()
            itemsRepository.addItems(itemsDto.toItems())

            emit(Resource.Success(Unit))

        } catch (e: Exception) {

            emit(Resource.Failure(e.message))

        }
    }
}