package com.example.popmaster

class Leaderboard() {
    private val topScores = Array(10) { _ -> Player("No Game Played", 0) }

    fun update(username: String, score: Int) {
        for(i in topScores.indices){
            if(score > topScores[i].score) {
                addPlayer(i, Player(username, score))
                break
            }
        }
    }

    fun addPlayer(startIndex: Int, player: Player) {
        if(startIndex < topScores.size) {
            val playerToMoveDown = topScores[startIndex]
            topScores[startIndex] = player
            addPlayer(startIndex + 1, playerToMoveDown)
        }
    }

    fun getTopScores(): Array<Player> {
        return topScores
    }

}