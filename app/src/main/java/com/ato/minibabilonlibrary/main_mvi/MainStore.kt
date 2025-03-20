package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.main_mvi.elements.MainAction
import com.ato.minibabilonlibrary.main_mvi.elements.MainEffect
import com.ato.minibabilonlibrary.main_mvi.elements.MainState
import com.ato.minibabilonlibrary.main_mvi.middleware.LibrarySettingsMiddleware
import com.ato.minibabilonlibrary.main_mvi.ui.MainUiMapper
import com.ato.minibabilonlibrary.main_mvi.ui.UiMainState
import com.ato.mvicore.Store


class MainStore : Store<MainState, MainAction, MainEffect, UiMainState>(
    initialState = MainState.DEFAULT_STATE,
    reducer = MainReducer(),
    publisher = MainPublisher(),
    stateMapper= { MainUiMapper(it) },
    middlewares = listOf(
        LibrarySettingsMiddleware()
    )
)


