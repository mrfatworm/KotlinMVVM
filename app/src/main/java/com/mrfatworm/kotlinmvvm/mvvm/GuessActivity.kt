package com.mrfatworm.kotlinmvvm.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mrfatworm.kotlinmvvm.R
import kotlinx.android.synthetic.main.activity_guess.*

class GuessActivity : AppCompatActivity() {

    lateinit var viewModel: GuessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)

        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)

        viewModel.answer.observe(this, Observer {
            tv_answer.text = it
        })

        viewModel.times.observe(this, Observer {
            tv_guess_times.text = it.toString()
        })

        btn_guess.setOnClickListener(){
            val guessNum = edt_guess.text.toString().toInt()
            viewModel.guess(guessNum)
        }

    }
}