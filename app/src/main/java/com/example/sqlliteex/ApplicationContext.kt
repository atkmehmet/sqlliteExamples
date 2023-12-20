package com.example.sqlliteex

import android.app.Application
import android.content.Context

class ApplicationContext:Application() {
    init {
    app=this
    }

    companion object{
        private  lateinit var app:Application
        fun getAppContext(): Context = app.applicationContext
    }
}