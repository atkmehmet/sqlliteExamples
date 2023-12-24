package com.example.sqlliteex.representation.mainScreen

sealed class mainScreenEvent {
    data class   personName(val name:String):mainScreenEvent()
    data class   personSurname(val surname:String):mainScreenEvent()

    data class   personId(val id:Int):mainScreenEvent()
    data class   bookName(val bookName:String):mainScreenEvent()
    data class   bookWriter(val bookWriter:String):mainScreenEvent()
    data class expandedChange(val expanded:Boolean):mainScreenEvent()
   data class  currentValue(val dropMenuValue:String):mainScreenEvent()
    object  personCommit:mainScreenEvent()
    object addBook:mainScreenEvent()
}