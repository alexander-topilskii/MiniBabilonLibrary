package com.ato.minibabilonlibrary.main_mvi

import com.ato.minibabilonlibrary.MainState
import com.ato.minibabilonlibrary.main_mvi.MainAction.ToggleVisibility
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateAlphabet
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateLineCount
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdatePageCount
import com.ato.minibabilonlibrary.main_mvi.MainAction.UpdateSymbols
import com.ato.mvicore.Publisher
import com.ato.mvicore.Reducer
import com.ato.mvicore.Store


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