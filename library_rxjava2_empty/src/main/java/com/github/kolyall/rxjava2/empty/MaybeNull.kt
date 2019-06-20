package com.github.kolyall.rxjava2.empty

import io.reactivex.Maybe
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.SchedulerSupport

object MaybeNull {

    @CheckReturnValue
    @NonNull
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T> just(item: T?): Maybe<T> {
        return if (item == null) {
            Maybe.empty()
        } else {
            Maybe.just(item)
        }
    }

}
