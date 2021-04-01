package com.mrfatworm.kotlinmvvm.mvp

//Model
data class User(var firstName: String, var lastName: String)

class Server{
    fun requestName(): User {
        return User("LanceHeight", "Lee")
    }
}

//Present
class MvpActivityPresenter(private val view: IMvp){

    fun requestUserName(){
        val user = Server().requestName()
        view.receiveName("${user.firstName} ${user.lastName}")
    }

}