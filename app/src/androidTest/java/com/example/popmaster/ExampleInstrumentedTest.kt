package com.example.popmaster

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    private val mainActivityRobot = MainActivityRobot()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.popmaster", appContext.packageName)
    }

    @Test
    fun mainTitleAppears() {
        mainActivityRobot.apply {
            gameTitleAppears()
            scoreAppears()
            questionAppears()
            buttonForRightAnswerAppears()
            buttonForWrongAnswerAppears()
        }
    }

    @Test
    fun bindingAllowsTextOverwrite() {
        mainActivityRobot.apply {
            textForWrongAnswerButtonUpdated()
            textForRightAnswerButtonUpdated()
        }
    }

    @Test
    fun correctUpdatesScore() {
        mainActivityRobot.apply {
            clickCorrectButton()
            checkScoreUpdated()
        }
    }

    @Test
    fun responseUpdatesQuestionNumber() {
        mainActivityRobot.apply {
            clickCorrectButton()
            checkQuestionUpdated()
        }
    }

    //Test for clicking correct answer
    //Test for clicking incorrect answer
}