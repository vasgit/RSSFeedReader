package com.test.rssfeedreader.di

import com.test.rssfeedreader.utils.formatters.TimeFormatter
import org.koin.dsl.module

val formattersModule = module {

    single { TimeFormatter(get()) }

}