package com.mrfatworm.lib

class Person {
    var weight = 0f
    var height = 0f

    fun getBmi():Float{
        return  weight / (height * height)
    }
}