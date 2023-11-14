package com.olefaent.kanyeapp

import android.app.Application
import com.olefaent.kanyeapp.data.AppContainer
import com.olefaent.kanyeapp.data.DefaultAppContainer

class KanyeApplication: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = DefaultAppContainer()
    }
}