package com.example.popmaster

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

class MainActivityRobot {

    fun gameTitleAppears() {
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.withText("Popmaster")))
    }

    fun scoreAppears() {
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.withId(R.id.score)))
    }

    fun questionAppears() {
        Espresso.onView(withId(R.id.question))
            .check(ViewAssertions.matches(ViewMatchers.withId(R.id.question)))
    }

    fun buttonForWrongAnswerAppears() {
        Espresso.onView(withId(R.id.incorrectButton))
            .check(ViewAssertions.matches(ViewMatchers.withId(R.id.incorrectButton)))
    }

    fun buttonForRightAnswerAppears() {
        Espresso.onView(withId(R.id.correctButton))
            .check(ViewAssertions.matches(ViewMatchers.withId(R.id.correctButton)))
    }

    fun textForWrongAnswerButtonUpdated() {
        Espresso.onView(withId(R.id.correctButton))
            .check(ViewAssertions.matches(ViewMatchers.withText("Correct")))
    }
    fun textForRightAnswerButtonUpdated() {
        Espresso.onView(withId(R.id.incorrectButton))
            .check(ViewAssertions.matches(ViewMatchers.withText("Incorrect")))
    }

    fun clickCorrectButton() {
        R.id.correctButton.clickView()
    }

    fun checkScoreUpdated() {
        Espresso.onView(withId(R.id.score))
            .check(ViewAssertions.matches(ViewMatchers.withText("Score: 3")))
    }

    fun Int.clickView() {
        Espresso.onView(withId(this))
            .perform(ViewActions.click())
    }

    fun checkQuestionUpdated() {
        Espresso.onView(withId(R.id.question))
            .check(ViewAssertions.matches(ViewMatchers.withText("Question: 2")))
    }


}