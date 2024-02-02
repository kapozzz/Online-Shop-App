package com.example.shop_app.domain.use_cases

import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.repositories.ItemsRepository
import javax.inject.Inject

class UpdateItemUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke(item: Item) {
        itemsRepository.updateItem(item)
    }

}