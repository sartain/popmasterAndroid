package com.example.popmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.popmaster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //Can then access the binding values
        binding.correctButton.text = getString(R.string.correct)
        binding.incorrectButton.text = getString(R.string.incorrect)
        updateQuestionString(binding)
        updateScoreString(binding)
        questionListener(binding)
        setContentView(binding.root)
    }
    val controller = Popmaster()

    fun questionListener(binding: ActivityMainBinding) {
        binding.correctButton.setOnClickListener { controller.answerQuestion(true)
            updateQuestionString(binding)
            updateScoreString(binding)
        }
        binding.incorrectButton.setOnClickListener { controller.answerQuestion(false)
            updateQuestionString(binding)
            updateScoreString(binding)
        }
    }

    fun updateQuestionString(binding: ActivityMainBinding) {
        binding.question.text = "Question: " + controller.getQuestion().toString()
    }

    fun updateScoreString(binding: ActivityMainBinding) {
        binding.score.text = "Score: " + controller.totalScore().toString()
    }

}