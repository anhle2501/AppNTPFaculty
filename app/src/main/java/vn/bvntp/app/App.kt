package vn.bvntp.app

import android.app.Application

import vn.bvntp.app.repository.AppContainer

class App : Application(){


    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}


