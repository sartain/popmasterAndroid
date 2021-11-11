package com.example.popmaster

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

class MainActivityRobot {

    fun gameTitleAppears() {
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.withText("Popmaster")))
    }
}