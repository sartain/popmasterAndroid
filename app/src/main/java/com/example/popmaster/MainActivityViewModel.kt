package com.example.popmaster

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ScreenInfo (val index : Int) {
    SCORE(0),
    QUESTION(1)
}

class MainActivityViewModel : ViewModel() {
    private val maxQuestions = 10
    private val scorecard = Scorecard()
    private var currentQuestion = 1
    private val leaderboard = Leaderboard()
    private var _screenInfoLiveData: MutableLiveData<MutableList<Int>> = MutableLiveData<MutableList<Int>>()
    private var gameOver = false

    fun loadData() {
        _screenInfoLiveData.value = mutableListOf(totalScore(), currentQuestion)
    }

    var screenInfoLiveData = _screenInfoLiveData

    fun totalScore(): Int {
        return scorecard.score
    }

    fun updateLeaderboard(username: String) {
        leaderboard.update(username, totalScore())
        reset()
    }

    private fun updateScoreWithCorrectAnswer() {
        scorecard.updateCorrectAnswer(currentQuestion)
    }

    private fun updateScreenInfoLiveData() {
        val updatedList = mutableListOf<Int>(scorecard.score, currentQuestion)
        _screenInfoLiveData.value = updatedList
    }

    private fun moveToNextQuestion() {
        currentQuestion += 1
        if(currentQuestion > 10) {
            gameOver = true
            currentQuestion = 10
        }
    }

    fun answerQuestion(correct: Boolean) {
        if(!gameOver) {
            if (correct)
                updateScoreWithCorrectAnswer()
            moveToNextQuestion()
        }
        updateScreenInfoLiveData()
    }
    private fun reset() {
        gameOver = false
        if(currentQuestion > maxQuestions) {
            scorecard.reset()
        }
        currentQuestion = 1
    }

    fun getLeaderboard(): Array<Player> {
        return leaderboard.getTopScores()
    }
}