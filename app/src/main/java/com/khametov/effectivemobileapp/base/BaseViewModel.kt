package com.khametov.effectivemobileapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.khametov.effectivemobileapp.common.error.ExceptionListener
import com.khametov.effectivemobileapp.common.error.coroutines.CoroutineErrorHandler

abstract class BaseViewModel<S : BaseViewState, E : BaseViewEvent>(
    initialState: S,
    val dispatcherProvider: CoroutinesDispatcherProvider = CoroutinesDispatcherProvider(),
) : ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    private val viewState: StateFlow<S> get() = _viewState

    protected val intent =
        MutableSharedFlow<E>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        intent.onEach(::observe).launchIn(viewModelScope)
    }

    protected fun updateState(block: S.() -> S) {
        _viewState.value = block.invoke(viewState.value)
    }

    protected fun updateStateFromIo(block: S.() -> S) {
        viewModelScope.launch(context = dispatcherProvider.main) {
            _viewState.value = block.invoke(viewState.value)
        }
    }

    protected val viewStateData
        get() = viewState.value

    protected abstract fun observe(event: E)

    fun perform(viewEvent: E) = intent.tryEmit(viewEvent)
    fun viewState(): StateFlow<S> = viewState

    protected fun launchCoroutine(
        isCancelDelay: Boolean = false,
        exceptionListener: ExceptionListener = ExceptionListener { false },
        body: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(
            context = CoroutineErrorHandler(exceptionListener = exceptionListener),
            block = {

                if (!isCancelDelay) {
                    delay(400)
                }
                body()
            }
        )
    }

    protected fun launchIOCoroutine(
        exceptionListener: ExceptionListener = ExceptionListener { false },
        body: suspend CoroutineScope.() -> Unit,
    ): Job {
        return launchCoroutine(exceptionListener = exceptionListener) {
            withContext(dispatcherProvider.io) {
                body()
            }
        }
    }
}