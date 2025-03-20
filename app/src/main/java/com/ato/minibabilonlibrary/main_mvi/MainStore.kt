package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.MainState
import com.ato.minibabilonlibrary.main_mvi.MainAction.*
import com.ato.minibabilonlibrary.mvi.Action
import com.ato.minibabilonlibrary.mvi.Publisher
import com.ato.minibabilonlibrary.mvi.Reducer
import com.ato.minibabilonlibrary.mvi.State
import com.ato.minibabilonlibrary.mvi.Store

class MainStore : Store<MainState, MainAction, MainEffect>(
    initialState = MainState(
        text = "алфавит 10 символов (0123456789) без знаков препинания и пробелов\n" +
                "книга содержит 2 страницы\n" +
                "на каждой странице 10 строк\n" +
                "в строке 20 символов"
    ),
    reducer = MainReducer(),
    publisher = MainPublisher()
) {

}

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


class MainReducer : Reducer<MainState, MainAction> {

    override fun reduce(state: MainState, action: MainAction): MainState {
        when (action) {
            is UpdateAlphabet -> {
                return state.copy(alphabet = action.newAlphabet)
            }

            is UpdatePageCount -> {
                return state.copy(page = action.newPageCount)
            }

            is UpdateLineCount -> {
                return state.copy(line = action.newLineCount)
            }

            is UpdateSymbols -> {
                return state.copy(symbols = action.newSymbolCount)
            }

            is ToggleVisibility -> {
                return state.copy(isSettingsVisible = !state.isSettingsVisible)
            }
        }
    }
}