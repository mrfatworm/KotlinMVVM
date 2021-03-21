package com.mrfatworm.kotlinmvvm

import android.view.View

//Model
data class User(var firstName: String, var lastName: String)

class Server{
    fun requestName(): User{
        return User("Lance", "Lee")
    }
}

//Present
class MainActivityPresenter(private val view: IMvp){

    fun requestUserName(){
        val user = Server().requestName()
        view.receiveName("${user.firstName} ${user.lastName}")
    }

}