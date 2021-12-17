package com.example.popmaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
    private var _scoreLiveData: MutableLiveData<Int> = MutableLiveData()
    private var _questionLiveData: MutableLiveData<Int> = MutableLiveData()
    private val scorecard = Scorecard()
    private var currentQuestion = 1
    private val leaderboard = Leaderboard()

    fun loadData() {
        _scoreLiveData.value = scorecard.score
        _questionLiveData.value = 0
    }

    fun getScore(): LiveData<Int> {
        return _scoreLiveData
    }

    val questionLiveData: LiveData<Int> = _questionLiveData

    val scoreLiveData: LiveData<Int> = _scoreLiveData

    fun totalScore(): Int {
        return scorecard.score
    }

    fun updateLeaderboard(username: String) {
        leaderboard.update(username, totalScore())
        reset()
    }

    fun correctAnswer() {
        scorecard.updateCorrectAnswer(currentQuestion)
        _scoreLiveData.value = scorecard.score
    }

    fun moveToNextQuestion() {
        currentQuestion += 1
        _questionLiveData.value = currentQuestion
    }

    fun answerQuestion(correct: Boolean) {
        if(currentQuestion <= 10) {
            if (correct)
                correctAnswer()
            if(currentQuestion != 10)
               moveToNextQuestion()
        }
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