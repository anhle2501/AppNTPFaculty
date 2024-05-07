package vn.bvntp.app

import android.app.Application
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import vn.bvntp.app.repository.AppContainer

class App : Application(){


    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()





    }






}


