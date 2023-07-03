package com.teko.tekotechv1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.teko.tekotechv1.base.TestSchedulerProvider
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
@DelicateCoroutinesApi
open class BaseViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val executor = InstantTaskExecutorRule()

    val testScheduler = TestScheduler()
    val schedulers = TestSchedulerProvider(testScheduler)

    @Before
    fun baseSetup() {
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun baseTearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
