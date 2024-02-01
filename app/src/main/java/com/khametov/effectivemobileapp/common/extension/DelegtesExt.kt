package com.khametov.effectivemobileapp.common.extension

inline fun <T> uiLazy(crossinline operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}
