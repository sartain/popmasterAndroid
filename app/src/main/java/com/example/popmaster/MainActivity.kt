package com.example.popmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.popmaster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel : Popmaster by viewModels()
        viewModel.loadData()
        viewModel.getQuestionLive().observe(this, {
            questionNumber -> binding.question.text = "Question: $questionNumber"
        })
        viewModel.getScore().observe(this, {
            score -> binding.score.text = "Score: $score"
        })
        binding.correctButton.text = getString(R.string.correct)
        binding.incorrectButton.text = getString(R.string.incorrect)
        setContentView(binding.root)
    }


}