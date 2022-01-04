package com.example.popmaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ScreenInfo (val index : Int) {
    SCORE(0),
    QUESTION(1)
}

class MainActivityViewModel : ViewModel() {
    private val scorecard = Scorecard()
    private var currentQuestion = 1
    private val leaderboard = Leaderboard()
    private var _screenInfoLiveData: MutableLiveData<MutableList<Int>> = MutableLiveData<MutableList<Int>>()

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

    fun correctAnswer() {
        scorecard.updateCorrectAnswer(currentQuestion)
    }

    fun updateScreenInfoLiveData() {
        val updatedList = mutableListOf<Int>(scorecard.score, currentQuestion)
        _screenInfoLiveData.value = updatedList
    }

    fun moveToNextQuestion() {
        currentQuestion += 1
    }

    fun answerQuestion(correct: Boolean) {
        if(currentQuestion <= 10) {
            if (correct)
                correctAnswer()
            if(currentQuestion != 10)
               moveToNextQuestion()
        }
        updateScreenInfoLiveData()
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