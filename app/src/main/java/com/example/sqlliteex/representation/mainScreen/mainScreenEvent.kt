package com.example.sqlliteex.representation.mainScreen

sealed class mainScreenEvent {
    data class   personName(val name:String):mainScreenEvent()
    data class   personSurname(val surname:String):mainScreenEvent()

    object  personCommit:mainScreenEvent()
}