package com.mrfatworm.kotlinmvvm

import android.app.Activity
import android.content.Context
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
        val userid = getSharedPreferences("atm", Context.MODE_PRIVATE)
            .getString("PREF_USERID", "66")
        edt_id.setText(userid)
    }

    fun login(view: View){
        val userId = edt_id.text.toString()
        val password = edt_password.text.toString()

        if(userId == "lance" && password == "861209"){
            getSharedPreferences("atm", Context.MODE_PRIVATE)
                .edit()
                .putString("PREF_USERID", userId)
                .apply()
            Toast.makeText(this, "Login succeeded", Toast.LENGTH_SHORT).show()
            intent.putExtra("LOGIN_USERID", userId)
            intent.putExtra("LOGIN_PASSWD", password)
            setResult(Activity.RESULT_OK, intent)
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