package com.test.rssfeedreader.di

import com.test.rssfeedreader.utils.extensions.getLocale
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val miscellaneousModule = module {

    factory { androidContext().getLocale() }

}