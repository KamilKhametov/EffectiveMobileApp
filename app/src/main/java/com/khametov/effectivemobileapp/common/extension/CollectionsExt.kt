package com.khametov.effectivemobileapp.common.extension

fun <T> List<T>.firstAndDo(block: (T) -> Unit) {
    if (isNotEmpty()) {
        block.invoke(first())
    }
}

inline fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    for ((index, item) in this.withIndex()) {
        if (predicate(item))
            return index
    }
    return null
}

fun <T> List<T>.indexOfFirstOrNull(element: T): Int? {
    for (index in indices) {
        if (element == this[index]) {
            return index
        }
    }

    return null
}

inline fun <reified T> List<Any>.findByType(
    condition: (T) -> Boolean = { true }
): T? {
    return find { it is T && condition(it) } as? T
}

inline fun <reified T> List<Any>.indexOfType(): Int {
    for ((index, item) in this.withIndex()) {
        if (item is T) return index
    }
    return -1
}