package com.zacoding.cmp.weather

import android.app.Application
import di.initKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}