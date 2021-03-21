package com.mrfatworm.lib

fun main(){
    println("Hello World")
    val person = Person()
    person.apply {
        height = 1.7f
        weight = 65f
    }

    println("BMI = ${person.getBmi()}")
}

class HelloWorld {
}