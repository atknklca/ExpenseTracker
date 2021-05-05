package com.example.expensetracker.functions

import com.example.expensetracker.model.Expense
import java.text.DecimalFormat
import java.util.ArrayList

class Functions {
    fun convert(moneyType: Int,presentItem: Expense,presentPrice:Int, list:ArrayList<Double>): String {

        when(moneyType){

            1 ->{   //tl olarak kaydedilmiş fiyatların dönüşümleri
                when(presentItem.currencyType){ //dönüşümü yapılacak olan para birimine göre hesaplamalar

                    1->{    //tl - dönüşüm yapılmaz
                        return convertPrice(0,0,presentPrice,list)
                        // holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(presentPrice)} TL"
                    }
                    2->{    //sterlin to try
                        return convertPrice(1,0,presentPrice,list)
                        //  val result = (1.0/list[1])*list[0]*presentPrice.toDouble()
                        //  holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    }
                    3->{    //usd to try
                        return convertPrice(2,0,presentPrice,list)
                        // val result = (1/list[2])*list[0]*presentPrice.toDouble()
                        // holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    }
                    4->{    //eur to try
                        return convertPrice(3,0,presentPrice,list)
                        // val result = (1/list[3])*list[0]*presentPrice.toDouble()
                        //  holder.itemBinding.textViewPrice.text = "${DecimalFormat("##.#").format(result)} TL"
                    } else -> return "error"

                }
            }
            2 -> {
                when(presentItem.currencyType){
                    1->{
                        return convertPrice(0,1,presentPrice,list)
                    }
                    2->{    //sterlin to try
                        return convertPrice(1,1,presentPrice,list)
                    }
                    3->{    //usd to try
                        return convertPrice(2,1,presentPrice,list)
                    }
                    4->{    //eur to try
                        return convertPrice(3,1,presentPrice,list)
                    }
                    else ->return "error"
                }}
            3 ->{
                when(presentItem.currencyType){
                    1->{    //tl - dönüşüm yapılmaz
                        return convertPrice(0,2,presentPrice,list)
                    }
                    2->{    //sterlin to try
                        return convertPrice(1,2,presentPrice,list)
                    }
                    3->{    //usd to try
                        return convertPrice(2,2,presentPrice,list)
                    }
                    4->{    //eur to try
                        return convertPrice(3,2,presentPrice,list)
                    }
                    else ->return "error"
                }
            }
            4 ->{
                when(presentItem.currencyType){
                    1->{    //tl - dönüşüm yapılmaz
                        return convertPrice(0,3,presentPrice,list)
                    }
                    2->{    //sterlin to try
                        return convertPrice(1,3,presentPrice,list)
                    }
                    3->{    //usd to try
                        return convertPrice(2,3,presentPrice,list)
                    }
                    4->{    //eur to try
                        return convertPrice(3,3,presentPrice,list)
                    }
                    else -> return "error"
                }

            } else -> return "error"

        }
    }
    private fun convertPrice(x: Int, y: Int, presentPrice: Int,list:ArrayList<Double>): String {
        val result = (1 / list[x]) * list[y] * presentPrice.toDouble()
        when (y) {
            0 -> return "${DecimalFormat("##.#").format(result)} ₺"
            1 -> return "${DecimalFormat("##.#").format(result)} £"
            2 -> return "${DecimalFormat("##.#").format(result)} €"
            3 -> return "${DecimalFormat("##.#").format(result)} $"
            else -> return "error"
        }
    }
    private fun convertPriceDouble(x: Int, y: Int, presentPrice: Int,list:ArrayList<Double>): Double {
        val result = (1 / list[x]) * list[y] * presentPrice.toDouble()
        return result
    }

    fun totalPrice(moneyType:Int, moneyList:ArrayList<Double>, expenseList: List<Expense>):Double{

        var totalPrice = 0.0

        for (presentItem in expenseList){
            when(moneyType) {

                1 -> {   //tl olarak kaydedilmiş fiyatların dönüşümleri
                    when (presentItem.currencyType) { //dönüşümü yapılacak olan para birimine göre hesaplamalar

                        1 -> {    //tl - dönüşüm yapılmaz
                            totalPrice += convertPriceDouble(0, 0, presentItem.expensePrice,moneyList)
                        }
                        2 -> {    //sterlin to try
                            totalPrice += convertPriceDouble(1, 0, presentItem.expensePrice,moneyList)
                        }
                        3 -> {    //usd to try
                            totalPrice += convertPriceDouble(2, 0, presentItem.expensePrice,moneyList)
                        }
                        4 -> {    //eur to try
                            totalPrice += convertPriceDouble(3, 0, presentItem.expensePrice,moneyList)
                        }
                    }
                }
                2 -> {
                    when (presentItem.currencyType) {
                        1 -> {
                            totalPrice += convertPriceDouble(0, 1, presentItem.expensePrice,moneyList)
                        }
                        2 -> {    //sterlin to try
                            totalPrice += convertPriceDouble(1, 1, presentItem.expensePrice,moneyList)
                        }
                        3 -> {    //usd to try
                            totalPrice += convertPriceDouble(2, 1, presentItem.expensePrice,moneyList)                        }
                        4 -> {    //eur to try
                            totalPrice += convertPriceDouble(3, 1, presentItem.expensePrice,moneyList)                        }
                    }
                }
                3 -> {
                    when (presentItem.currencyType) {
                        1 -> {    //tl - dönüşüm yapılmaz
                            totalPrice += convertPriceDouble(0, 2, presentItem.expensePrice,moneyList)
                        }
                        2 -> {    //sterlin to try
                            totalPrice += convertPriceDouble(1, 2, presentItem.expensePrice,moneyList)
                        }
                        3 -> {    //usd to try
                            totalPrice += convertPriceDouble(2, 2, presentItem.expensePrice,moneyList)
                        }
                        4 -> {    //eur to try
                            totalPrice += convertPriceDouble(3, 2, presentItem.expensePrice,moneyList)
                        }
                    }
                }
                4 -> {
                    when (presentItem.currencyType) {
                        1 -> {    //tl - dönüşüm yapılmaz
                            totalPrice += convertPriceDouble(0, 3, presentItem.expensePrice,moneyList)
                        }
                        2 -> {    //sterlin to try
                            totalPrice += convertPriceDouble(1, 3, presentItem.expensePrice,moneyList)
                        }
                        3 -> {    //usd to try
                            totalPrice += convertPriceDouble(2, 3, presentItem.expensePrice,moneyList)
                        }
                        4 -> {    //eur to try
                            totalPrice += convertPriceDouble(3, 3, presentItem.expensePrice,moneyList)
                        }
                    }
                }
            }
println(totalPrice)

        }

    return totalPrice
    }
}