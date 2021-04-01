package com.mrfatworm.kotlinmvvm.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mrfatworm.kotlinmvvm.databinding.ActivityMvpBinding
import java.lang.Exception

class MvpActivity : AppCompatActivity(), IMvp {

    private var presenter = MvpActivityPresenter(this)
    private lateinit var binding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.requestUserName()
    }

    override fun receiveName(name: String) {
        binding.tvWeight.text = name
    }

    fun bmi(view: View) {
        try {
            val weight = binding.edtWeight.text.toString().toFloat()
            val height = binding.edtHeight.text.toString().toFloat()
            val bmi = weight / (height * height)
            Toast.makeText(this, "BMI = $bmi", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(this, "You must input value", Toast.LENGTH_SHORT).show()
        }
    }

    fun help(view: View) {
        AlertDialog.Builder(this)
            .setMessage("Input your height(meter) and weight")
            .setTitle("Hint")
            .setPositiveButton("OK", null)
            .show()
    }
}