package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.main_mvi.elements.MainAction
import com.ato.minibabilonlibrary.main_mvi.elements.MainEffect
import com.ato.minibabilonlibrary.main_mvi.elements.MainState
import com.ato.mvicore.Publisher

class MainPublisher : Publisher<MainState, MainAction, MainEffect> {
    override fun publish(state: MainState, action: MainAction): MainEffect? {
        return when {
            true -> {
                null
            }

            else -> MainEffect.ShowToast("Settings are updated")
        }
    }
}