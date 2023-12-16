package com.example.section15.Util

fun calculateTotalTips(totalinput: Int, sliderValue: Int): Int {
    return   if (totalinput>1 && totalinput.toString().isNotEmpty())
        (totalinput * sliderValue) / 100 else 0
}


fun calculateTotalPerson(
    totalBill: Int,
    splitBy:Int,
    totalPercentage: Int
): Int{
   val bill = calculateTotalTips(totalinput= totalBill,sliderValue= totalPercentage) + totalBill

  return (bill/splitBy)
}