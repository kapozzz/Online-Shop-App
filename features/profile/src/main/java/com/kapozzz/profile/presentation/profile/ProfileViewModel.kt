package com.kapozzz.profile.presentation.profile

import androidx.lifecycle.viewModelScope
import com.kapozzz.common.common.BaseViewModel
import com.kapozzz.domain.repositories.ItemsRepository
import com.kapozzz.domain.repositories.UserRepository
import com.kapozzz.profile.presentation.profile.ProfileEffect
import com.kapozzz.profile.presentation.profile.ProfileEvent
import com.kapozzz.profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val itemsRepository: ItemsRepository
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {
    override fun createInitialState(): ProfileState {
        return ProfileState()
    }

    init {
        initialize()
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.ExitFromAccount -> exit()
            ProfileEvent.OpenLiked -> openLiked()
            ProfileEvent.OpenSupport -> openSupport()
        }
    }

    private fun initialize() {
        viewModelScope.launch {
            val user = userRepository.getUser()
            currentState.user.value = user
        }
        viewModelScope.launch {
            itemsRepository.getLikedItemsCount().collect {
                currentState.liked.value = it
            }
        }
    }

    private fun exit() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.exit()
        }
        setEffect(ProfileEffect.ExitFromAccount)
    }

    private fun openLiked() {
        setEffect(ProfileEffect.OpenLiked)
    }

    private fun openSupport() {
        // todo
    }

}