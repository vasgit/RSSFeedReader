package com.test.rssfeedreader.di

import com.test.rssfeedreader.utils.providers.ConnectionProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val providersModule = module {

    single { ConnectionProvider(androidApplication()) }

}


