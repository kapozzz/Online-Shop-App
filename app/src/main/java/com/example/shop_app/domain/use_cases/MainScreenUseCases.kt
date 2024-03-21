package com.example.shop_app.domain.use_cases

import javax.inject.Inject

data class MainScreenUseCases @Inject constructor(
    val sortItemsUseCase: SortItemsUseCase,
    val refreshDataUseCase: RefreshDataUseCase
)