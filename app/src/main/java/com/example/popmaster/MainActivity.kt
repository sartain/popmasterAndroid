package com.example.popmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.popmaster.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel : MainActivityViewModel by viewModels()
        viewModel.loadData()
        viewModel.screenInfoLiveData.observe(this, { score ->
            binding.score.text = getString(R.string.score, score[ScreenInfo.SCORE.index])
            binding.question.text = getString(R.string.question, score[ScreenInfo.QUESTION.index])
        })
        binding.correctButton.text = getString(R.string.correct)
        binding.incorrectButton.text = getString(R.string.incorrect)
        questionListener(binding, viewModel)
        setContentView(binding.root)
    }

    private fun questionListener(binding: ActivityMainBinding, viewModel : MainActivityViewModel) {
        binding.correctButton.setOnClickListener {
            viewModel.answerQuestion(true)
        }
        binding.incorrectButton.setOnClickListener {
            viewModel.answerQuestion(false)
        }
    }
}