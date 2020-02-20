package com.bins.gojeksample

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    public val main = ActivityTestRule(MainActivity::class.java, true)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bins.gojeksample", appContext.packageName)
    }

    @Test
    fun testActionBarText(){
        var  expectedResult :String = getApplicationContext<Context>().getString(R.string.app_name)
        onView(withId(R.id.toolbar_title)).check(matches(withText(expectedResult)))
    }

    @Test
    fun testRecycleView(){
        onView(withId(R.id.recyclerViewData))
            .perform(RecyclerViewActions.actionOnItemAtPosition<DataViewHolder>(0, click()))
    }

}
