package com.github.kolyall.rxjava2.empty

import io.reactivex.Maybe

fun <T, R> Maybe<T>.mapOrEmpty(mapper: (T) -> R?): Maybe<R> {
    return this.flatMap { item ->
        mapper(item)?.let { Maybe.just<R>(it) } ?: Maybe.empty()
    }
}