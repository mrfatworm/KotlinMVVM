package com.mrfatworm.kotlinmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mrfatworm.kotlinmvvm.mvp.MvpActivity
import com.mrfatworm.kotlinmvvm.mvvm.GuessActivity

class MainActivity : AppCompatActivity() {

    var login = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!login) {
            Intent(this, LoginActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    fun goBmi(view: View) {
        Intent(this, MvpActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun goGuess(view: View) {
        Intent(this, GuessActivity::class.java).apply {
            startActivity((this))
        }
    }
}