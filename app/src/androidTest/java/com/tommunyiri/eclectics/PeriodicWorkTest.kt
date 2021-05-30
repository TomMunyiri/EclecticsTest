package com.tommunyiri.eclectics

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker.Result
import androidx.work.testing.TestListenableWorkerBuilder
import com.tommunyiri.eclectics.worker.FetchArticlesWorker
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PeriodicWorkTest {
    private lateinit var context: Context
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }
    @Test
    fun testFetchArticlesWork() {
        // Get the ListenableWorker
        val worker = TestListenableWorkerBuilder<FetchArticlesWorker>(context).build()
        // Start the work synchronously
        val result = worker.startWork().get()
        assertThat(result, `is`(Result.success()))
    }
}