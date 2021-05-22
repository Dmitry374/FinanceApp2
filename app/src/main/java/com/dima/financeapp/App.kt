package com.dima.financeapp

import android.app.Application
import com.dima.financeapp.di.AppComponent
import com.dima.financeapp.di.AppModule
import com.dima.financeapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}