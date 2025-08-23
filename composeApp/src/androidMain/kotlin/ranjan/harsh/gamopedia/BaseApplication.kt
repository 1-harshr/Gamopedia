package ranjan.harsh.gamopedia

import android.app.Application
import ranjan.harsh.gamopedia.di.initKoin

class BaseApplication: Application()  {

    override fun onCreate() {
        super.onCreate()
        initKoin ()
    }
}