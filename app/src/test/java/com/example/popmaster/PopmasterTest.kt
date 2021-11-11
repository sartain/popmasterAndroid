package com.example.popmaster

import org.junit.Test

import org.junit.Assert.*

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


class PopmasterTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `Score increased by 3 for correct answer` () {
        val game = Popmaster()
        game.answerQuestion(true)
        assertEquals(3, game.totalScore())
    }

    @Test
    fun `Score increased by 6 for correct bonus answer` () {
        val game = Popmaster()
        game.answerQuestion(false)
        game.answerQuestion(false)
        game.answerQuestion(true)
        assertEquals(6, game.totalScore())
    }
    
    @Test
    fun `Score is 39 if all questions correct` () {
        val game = Popmaster()
        for(i in 0..9) {
            game.answerQuestion(true)
        }
        assertEquals(39, game.totalScore())
    }

    @Test
    fun `Add name and score to leaderboard at end of game` () {
        val game = Popmaster()
        for(i in 0..9) {
            game.answerQuestion(true)
        }
        game.updateLeaderboard("alex")
        assertEquals(39, game.getLeaderboard()[0].score)
        assertEquals("alex", game.getLeaderboard()[0].username)
    }

    @Test
    fun `Complete game playthrough total score resets` () {
        val game = Popmaster()
        for(i in 0..9) {
            game.answerQuestion(true)
        }
        game.updateLeaderboard("alex")
        assertEquals(0, game.totalScore())
    }

    @Test
    fun `Leaderboard updates if someone gets higher score` () {
        val game = Popmaster()
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

