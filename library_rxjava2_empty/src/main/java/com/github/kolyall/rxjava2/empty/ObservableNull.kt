package com.github.kolyall.rxjava2.empty

import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.SchedulerSupport

object ObservableNull {

    @CheckReturnValue
    @NonNull
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> just(item: T?): Observable<T> {
        return if (item == null) {
            Observable.empty()
        } else {
            Observable.just(item)
        }
    }

    @CheckReturnValue
    @NonNull
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> fromIterable(iterable: Iterable<T>?): Observable<T> {
        return if (iterable == null || iterable.count() == 0) {
            Observable.empty()
        } else {
            val filtered = iterable.filter { it != null }
            if (filtered.isEmpty()){
                return Observable.empty()
            }
            Observable.fromIterable(filtered)
        }
    }
}
