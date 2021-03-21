package com.mrfatworm.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), IMvp {

    private lateinit var tvMVP: TextView

    private var presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMVP = findViewById(R.id.tv_mvp)
        presenter.requestUserName()
    }

    override fun receiveName(name: String) {
        tvMVP.text = name
    }
}