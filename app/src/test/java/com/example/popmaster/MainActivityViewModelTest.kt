package com.example.popmaster

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/*
ToDo
    -Scoreboard of 10 questions = Done
    -Score increased by 3 for correct standard answer = Done
    -Score increased by 6 for correct bonus answer = Done
    -Score is 39 if all questions correct = Done
    -Bonus questions at Q: 3, 6, 9 = Done
    -Questions end at 10, final score given = Done
    -Add name to leaderboard at end with score = Done
 */

//Two live data -> Consider as one view state
//Fix issues in 'test'
//Consider test class runner

class MainActivityViewModelTest {
    val testSubject = MainActivityViewModel()
    private val observer: Observer<MutableList<Int>> =
        mockk(relaxed = true)

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setupTest() {
        testSubject.screenInfoLiveData.observeForever(observer)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `Score increased by 3 for correct answer` () {
        testSubject.answerQuestion(true)
        assertEquals(mutableListOf(3, 2), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Score increased by 6 for correct bonus answer` () {
        testSubject.answerQuestion(false)
        testSubject.answerQuestion(false)
        testSubject.answerQuestion(true)
        assertEquals(mutableListOf(6, 4), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Score cannot be larger than 39` () {
        for( i in 1..20) {
            testSubject.answerQuestion(true)
        }
        assertEquals(mutableListOf(39, 10), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Score is 39 if all questions correct` () {
        for(i in 0..9) {
            testSubject.answerQuestion(true)
        }
        assertEquals(mutableListOf(39, 10), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Add name and score to leaderboard at end of game` () {
        for(i in 0..9) {
            testSubject.answerQuestion(true)
        }
        testSubject.updateLeaderboard("alex")
        assertEquals(39, testSubject.getLeaderboard()[0].score)
        assertEquals("alex", testSubject.getLeaderboard()[0].username)
    }

    @Test
    fun `Complete game playthrough total score resets` () {
        for(i in 0..9) {
            testSubject.answerQuestion(true)
        }
        testSubject.updateLeaderboard("alex")
       //assertEquals(mutableListOf(39, 10), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Answer 10 questions cannot answer correct any more` () {
        for(i in 0..9) {
            testSubject.answerQuestion(false)
        }
        testSubject.answerQuestion(true)
        assertEquals(mutableListOf(0, 10), testSubject.screenInfoLiveData.value)
    }

    @Test
    fun `Leaderboard updates if someone gets higher score` () {
        val game = MainActivityViewModel()
        for(i in 0..8) {
            game.answerQuestion(true)
        }
        game.answerQuestion(false)
        game.updateLeaderboard("alex")
        assertEquals(36, game.getLeaderboard()[0].score)
        assertEquals("alex", game.getLeaderboard()[0].username)
        for(i in 0..9) {
            game.answerQuestion(true)
        }
        game.updateLeaderboard("bob")
        assertEquals(39, game.getLeaderboard()[0].score)
        assertEquals("bob", game.getLeaderboard()[0].username)
        assertEquals(36, game.getLeaderboard()[1].score)
        assertEquals("alex", game.getLeaderboard()[1].username)
    }
}

