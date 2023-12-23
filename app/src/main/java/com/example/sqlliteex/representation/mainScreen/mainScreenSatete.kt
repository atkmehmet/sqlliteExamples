package com.example.sqlliteex.representation.mainScreen

data class mainScreenSatete (
    val personName:String="",
    val personSurname: String="",
    val personCount:Int=0,
    val personId:Int=0,
    val bookName:String="",
    val bookWriter:String="",
    val expanded:Boolean = false,
    val currentValue:String="Emre"
)