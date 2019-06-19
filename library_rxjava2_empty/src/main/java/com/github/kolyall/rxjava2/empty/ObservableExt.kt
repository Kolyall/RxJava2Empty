package com.github.kolyall.rxjava2.empty

import io.reactivex.Observable

fun <T, R> Observable<T>.mapOrEmpty(mapper: (T) -> R?): Observable<R> {
    return this.flatMap { item ->
        mapper(item)?.let { Observable.just<R>(it) } ?: Observable.empty()
    }
}