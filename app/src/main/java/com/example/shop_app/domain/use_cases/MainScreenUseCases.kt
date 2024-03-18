package com.example.shop_app.domain.use_cases

import javax.inject.Inject

data class MainScreenUseCases @Inject constructor(
    val getItemsUseCase: GetItemsUseCase,
    val updateItemUseCase: UpdateItemUseCase
)