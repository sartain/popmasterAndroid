package com.example.popmaster

class Scorecard() {
    private var scoreArray = IntArray(10) { _ -> 0}
    val score get(): Int {
        return scoreArray.sum()
    }

    fun reset() {
        scoreArray = IntArray(10) { _ -> 0}
    }

    fun updateCorrectAnswer(questionNo: Int) {
        val bonusQuestion = questionNo % 3 == 0
        var pointsToAdd = 3
        if(bonusQuestion)
            pointsToAdd = 6
        scoreArray[questionNo - 1] = pointsToAdd
    }
}