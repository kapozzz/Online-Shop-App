package com.example.shop_app.presentation.signIn

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.shop_app.core.common.BaseViewModel
import com.example.shop_app.domain.model.User
import com.example.shop_app.domain.repositories.SignInRepository
import com.example.shop_app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : BaseViewModel<SignInEvent, SignInState, SignInEffect>() {

    override fun createInitialState(): SignInState {
        return SignInState()
    }

    init {
        viewModelScope.launch{

            val isFirstStart = signInRepository.isFirstStart()
            Log.d("shop_info", "$isFirstStart")

            if (!isFirstStart) {
                loadData()
            }
        }
    }

    override fun handleEvent(event: SignInEvent) {
        when (event) {
            SignInEvent.ValidateUserData -> {
                validateUser()
            }
        }
    }

    private suspend fun loadData() {
        signInRepository.itStarted().onEach {
            when (it) {
                is Resource.Failure -> {
                    throw IOException(it.message)
                }

                is Resource.Loading -> {
                    currentState.loading.value = true
                }

                is Resource.Success -> {
                    setEffect(SignInEffect.UserDataIsValidated)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun validateUser() {
        val user = User(
            name = currentState.name.value,
            surname = currentState.surname.value,
            mobilePhone = currentState.mobilePhone.value
        )
        viewModelScope.launch {
            signInRepository.saveUser(user)
            loadData()
        }
    }
}