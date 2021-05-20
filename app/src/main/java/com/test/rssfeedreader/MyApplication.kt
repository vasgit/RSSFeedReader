package com.test.rssfeedreader

import android.app.Application
import com.test.rssfeedreader.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        initDi()
    }


    private fun initDi() {
        startKoin {
            logger(EmptyLogger())
            androidContext(this@MyApplication)
            modules(applicationModules)
        }
    }


}