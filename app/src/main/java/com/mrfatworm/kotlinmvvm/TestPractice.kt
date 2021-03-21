package com.mrfatworm.kotlinmvvm

class Price{
    lateinit var rule: DiscountRule

    fun getPrice(price: Int): Int{
        val resultPrice = price * rule.getDiscountRate()
        return  resultPrice.toInt()
    }

    fun getPriceTitle(): String{
        return "We have ${rule.getDiscountTitle()} now !!!"
    }
}

class DiscountRule(private val type:Int){

    fun getDiscountRate(): Float{
        return when(type){
            1 -> 0.7F
            2 -> 0.8F
            else -> 1F
        }
    }

    fun getDiscountTitle(): String{
        return when(type){
            1 -> "Big Sale"
            2 -> "For Sale"
            else -> "Normal"
        }
    }

}

fun main(){
    val price = Price()
    price.rule = DiscountRule(1)
    println(price.getPrice(1000) == 700)
    println(price.getPrice(500) == 350)
    println(price.getPrice(2000) == 1400)
}
