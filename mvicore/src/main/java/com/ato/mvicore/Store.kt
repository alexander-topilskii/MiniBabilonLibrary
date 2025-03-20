package com.ato.mvicore

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


fun interface Reducer<S : State, A : Action> {
    fun reduce(state: S, action: A): S
}

fun interface Middleware<S : State, A : Action> {
    fun process(state: S, action: A, dispatch: (A) -> Unit)
}

interface Publisher<S : State, A : Action, E : Effect> {
    fun publish(state : S, action : A): E?
}

// Store: управляет состоянием, экшенами и эффектами
open class Store<S : State, A : Action, E : Effect, UI>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList(),
    private val publisher: Publisher<S, A, E>,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
    private val stateMapper: (S) -> UI,
    private val mappingDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<E>()
    val effects: SharedFlow<E> = _effects.asSharedFlow()

    private val _actions = MutableSharedFlow<A>()

    // ✅ UI-стейт с примененным маппингом
    private val _uiState = _state.map { state ->
        withContext(mappingDispatcher) { stateMapper(state) }
    }.stateIn(scope, SharingStarted.Eagerly, stateMapper(initialState))

    val uiState: StateFlow<UI> = _uiState

    init {
        // Обрабатываем экшены
        _actions.onEach { action ->
            middlewares.forEach { it.process(_state.value, action, ::dispatch) }

            val newState = reducer.reduce(_state.value, action)
            val newEffect = publisher.publish(_state.value, action)

            _state.update { newState }
            newEffect?.let { sendEffect(it) }
        }.launchIn(scope)
    }

    fun dispatch(action: A) = scope.launch {
        _actions.emit(action)
    }

    private fun sendEffect(effect: E) {
        _effects.tryEmit(effect)
    }
}