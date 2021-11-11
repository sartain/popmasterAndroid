package com.example.popmaster

class Popmaster {
    private val scorecard = Scorecard()
    private var currentQuestion = 1
    private val leaderboard = Leaderboard()

    fun totalScore(): Int {
        return scorecard.score
    }

    fun updateLeaderboard(username: String) {
        leaderboard.update(username, totalScore())
        reset()
    }

    fun answerQuestion(correct: Boolean) {
        if(correct) {
            scorecard.updateCorrectAnswer(questionNo = currentQuestion)
        }
        currentQuestion += 1
    }

    private fun reset() {
        if(currentQuestion > 10) {
            scorecard.reset()
        }
        currentQuestion = 1
    }

    fun getLeaderboard(): Array<Player> {
        return leaderboard.getTopScores()
    }
}