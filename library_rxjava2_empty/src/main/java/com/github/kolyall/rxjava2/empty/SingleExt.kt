package com.github.kolyall.rxjava2.empty

import io.reactivex.Single

fun <T, R> Single<T>.mapOrEmpty(mapper: (T) -> R?): Single<R> {
    return this.flatMap { item ->
        mapper(item)?.let { Single.just<R>(it) } ?: Single.never()
    }
}