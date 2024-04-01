package com.kapozzz.profile.presentation.support

import com.kapozzz.common.common.BaseViewModel
import com.kapozzz.profile.presentation.profile.ProfileEffect
import com.kapozzz.profile.presentation.profile.ProfileEvent
import com.kapozzz.profile.presentation.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor() :
    BaseViewModel<SupportEvent, SupportState, SupportEffect>() {
    override fun createInitialState(): SupportState {
        return SupportState()
    }

    override fun handleEvent(event: SupportEvent) {
       when(event) {
           SupportEvent.OnBackClick -> setEffect(SupportEffect.OnBackClick)
       }
    }

}