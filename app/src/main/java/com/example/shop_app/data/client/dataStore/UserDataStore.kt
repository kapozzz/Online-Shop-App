package com.example.shop_app.data.client.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shop_app.domain.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val userName = stringPreferencesKey("user_name")
private val userSurname = stringPreferencesKey("user_surname")
private val userMobilePhone = stringPreferencesKey("user_mobile_phone")

class UserDataStore(val context: Context) {

    val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_info")

    suspend fun saveUser(user: User) {
        context.userDataStore.edit { userInfo ->
            userInfo[userName] = user.name
            userInfo[userSurname] = user.surname
            userInfo[userMobilePhone] = user.mobilePhone
        }
    }

    suspend fun getUser(): User {
         return context.userDataStore.data
            .map { userInfo ->
                User(
                    name = userInfo[userName] ?: "",
                    surname = userInfo[userSurname] ?: "",
                    mobilePhone = userInfo[userMobilePhone] ?: ""
                )
            }.first()
    }

}