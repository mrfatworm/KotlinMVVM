package com.mrfatworm.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View){
        val userId = edt_id.text.toString()
        val password = edt_password.text.toString()

        if(userId == "lance" && password == "861209"){
            Toast.makeText(this, "Login succeeded", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            AlertDialog.Builder(this)
                .setTitle("ATM")
                .setMessage("Login failed")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    fun cancel(view: View) {

    }
}