package com.penguin.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * SearchScreen Benchmark
 */
@RunWith(AndroidJUnit4::class)
class SearchScreenBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun searchScreenDrakeSearch() = benchmarkRule.measureRepeated(
        packageName = "com.penguin.musicinfo",
        metrics = listOf(FrameTimingMetric()),
        iterations = ITERATIONS,
        setupBlock = {
            pressHome()
            startActivityAndWait()
        }
    ) {
        val searchBarButton = device.findObject(By.res("searchBarButton"))
        val searchBarTextField = device.findObject(By.res("searchBarTextField"))
        if (searchBarButton != null && searchBarTextField != null) {
            searchBarTextField.text = "Drake"
            searchBarButton.click()
        }
        // wait until window changes finish
        device.waitForIdle()
    }

    @Test
    fun searchScreenScrolling() = benchmarkRule.measureRepeated(
        packageName = "com.penguin.musicinfo",
        metrics = listOf(FrameTimingMetric()),
        iterations = ITERATIONS,
        setupBlock = {
            pressHome()
            startActivityAndWait()
        }
    ) {
        val searchBarButton = device.findObject(By.res("searchBarButton"))
        val searchBarTextField = device.findObject(By.res("searchBarTextField"))
        if (searchBarButton != null && searchBarTextField != null) {
            searchBarTextField.text = "Drake"
            searchBarButton.click()
        }

        device.findObject(By.res("searchScreenLazyColumn"))?.scroll(Direction.DOWN, 0.5F)
        // wait until window changes finish
        device.waitForIdle()
    }


    companion object {
        private const val ITERATIONS = 1
    }
}