package org.sirekanyan.`fun`

import android.app.Application

class FunApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        androidApplicationContext = this
    }
}