package com.github.kolyall.rxjava2.empty

import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EmptyObservableTest {

    private val testScheduler = TestScheduler()

    @Test
    fun test1() {
        ObservableNull.just(null)
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertComplete()
            .dispose()
    }

    @Test
    fun test2() {
        ObservableNull.just("1")
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertValue("1")
            .assertComplete()
            .dispose()
    }

    @Test
    fun test3() {
        ObservableNull.just(arrayListOf("1", null, "3", "4"))
            .flatMap { ObservableNull.fromIterable(it) }
            .toList()
            .toObservable()
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertValue(listOf("1", "3", "4"))
            .assertComplete()
            .dispose()
    }

    @Test
    fun test4() {
        Observable.range(1, 5)
            .mapOrEmpty {
                when (it) {
                    2 -> null
                    else -> it
                }
            }
            .toList()
            .toObservable()
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertValue(listOf(1,3,4,5))
            .assertComplete()
            .dispose()
    }
    @Test
    fun test5() {
        Observable.just(Pair<String, String?>("",null))
            .mapOrEmpty {it.second}
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertComplete()
            .dispose()
    }

    @Test
    fun test6() {
        Observable.just(Pair<String, String?>("",null))
            .mapOrEmpty {it.first}
            .subscribeOn(testScheduler)
            .observeOn(testScheduler)
            .test()
            .also { testScheduler.triggerActions() }
            .assertValue("")
            .assertComplete()
            .dispose()
    }
}