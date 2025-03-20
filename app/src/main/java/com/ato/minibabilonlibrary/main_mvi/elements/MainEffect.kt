package com.ato.minibabilonlibrary.main_mvi.elements

import com.ato.mvicore.Effect

sealed interface MainEffect: Effect {

    data class ShowToast(val message: String): MainEffect
}