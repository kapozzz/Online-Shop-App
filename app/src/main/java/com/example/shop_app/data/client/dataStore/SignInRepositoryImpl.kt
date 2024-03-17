package com.example.shop_app.data.client.dataStore

import android.util.Log
import com.example.shop_app.data.remote.dto.ItemsService
import com.example.shop_app.data.remote.dto.toItems
import com.example.shop_app.domain.model.User
import com.example.shop_app.domain.repositories.ItemsRepository
import com.example.shop_app.domain.repositories.SignInRepository
import com.example.shop_app.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore,
    private val itemsService: ItemsService,
    private val itemsRepository: ItemsRepository
) : SignInRepository {

    override suspend fun saveUser(user: User) {
        userDataStore.saveUser(user = user)
        settingsDataStore.itStarted()
    }

    override suspend fun isFirstStart(): Boolean {
        return settingsDataStore.isFirstStart()
    }

    override suspend fun itStarted(): Flow<Resource<Boolean>> {
        return flow {
            try {
                emit(Resource.Loading())
                withContext(Dispatchers.IO) {
                    val itemsDto = itemsService.getItems()
                    itemsRepository.addItems(itemsDto.toItems())
                    settingsDataStore.itStarted()
                }
                emit(Resource.Success(true))
            } catch (e: Exception) {
                emit(Resource.Failure(e.message))
            }
        }
    }
}