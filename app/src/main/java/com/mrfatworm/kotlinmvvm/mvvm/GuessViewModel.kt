package com.mrfatworm.kotlinmvvm.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel: ViewModel() {

    val answer = MutableLiveData<String>()
    val times = MutableLiveData<Int>()
    private var secret = Random.nextInt(10) + 1
    private var counter = 0

    fun guess(guessNum: Int){
        answer.value = when(secret - guessNum){
            0 -> "Correct"
            in 0 .. 10 -> "Bigger"
            else -> "Smaller"
        }
        counter++
        times.value = counter
    }
}