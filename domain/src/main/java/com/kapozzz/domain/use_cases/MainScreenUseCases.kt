package com.kapozzz.domain.use_cases

import javax.inject.Inject

data class MainScreenUseCases @Inject constructor(
    val sortItemsUseCase: SortItemsUseCase
)