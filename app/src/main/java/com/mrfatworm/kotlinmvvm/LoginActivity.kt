package com.mrfatworm.kotlinmvvm

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

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

        CoroutineScope(Dispatchers.IO).launch{
            val result =
                    URL("https://atm201605.appspot.com/login?uid=$userId&pw=$password")
                            .readText()
            Log.d("Result", result)

            if(result == "1"){
                getSharedPreferences("atm", Context.MODE_PRIVATE)
                        .edit()
                        .putString("PREF_USERID", userId)
                        .apply()
                runOnUiThread{
                    Toast.makeText(this@LoginActivity, "Login succeeded", Toast.LENGTH_SHORT).show()
                }
                intent.putExtra("LOGIN_USERID", userId)
                intent.putExtra("LOGIN_PASSWD", password)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                runOnUiThread{
                    AlertDialog.Builder(this@LoginActivity)
                            .setTitle("ATM")
                            .setMessage("Login failed")
                            .setPositiveButton("OK", null)
                            .show()
                }
            }
        }
    }

    fun cancel(view: View) {

    }
}