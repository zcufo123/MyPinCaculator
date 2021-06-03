package com.test.mypincaculator

import android.app.Application
import androidx.annotation.UiThread
import androidx.test.annotation.UiThreadTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.test.mypincaculator.viewmodel.MainViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ViewModelTest {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    @UiThreadTest
    fun mainViewModelTest1() {
        val mainViewModel = MainViewModel(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        )
        mainViewModel.enterPin("12")
        assert(mainViewModel.output.value.equals(appContext.getString(R.string.pin_too_short)))
    }

    @Test
    @UiThreadTest
    fun mainViewModelTest2() {
        val mainViewModel = MainViewModel(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        )
        mainViewModel.enterPin("12345678901234567890")
        assert(mainViewModel.output.value.equals(appContext.getString(R.string.pin_too_long)))
    }
}