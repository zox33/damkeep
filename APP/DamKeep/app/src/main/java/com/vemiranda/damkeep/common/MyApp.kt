package com.vemiranda.damkeep.common

import android.app.Application

class MyApp: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    companion object{
        lateinit var instace: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instace=this
    }
}