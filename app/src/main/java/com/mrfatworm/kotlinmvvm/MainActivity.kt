package com.mrfatworm.kotlinmvvm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mrfatworm.kotlinmvvm.mvp.MvpActivity
import com.mrfatworm.kotlinmvvm.mvvm.GuessActivity

class MainActivity : AppCompatActivity() {

    companion object{
        val RC_LOGIN = 30
        val REQUEST_CAMERA = 50
    }
    var login = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!login) {
            Intent(this, LoginActivity::class.java).apply {
                startActivityForResult(this, RC_LOGIN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_LOGIN){
            if (resultCode == Activity.RESULT_OK){
                val userId = data?.getStringExtra("LOGIN_USERID")
                val passwd = data?.getStringExtra("LOGIN_PASSWD")
                Log.d("Result", "$userId / $passwd")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ) {
            R.id.action_camera -> {
                Intent(this, CameraActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.action_contact -> {
                Intent(this, MaterialActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.action_help -> {

            }
        }
        return super.onOptionsItemSelected(item)
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