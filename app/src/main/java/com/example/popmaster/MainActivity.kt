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
        binding.correctButton.text = "Correct"
        binding.incorrectButton.text = "Incorrect"
        setContentView(binding.root)
    }
    val controller = Popmaster()
}