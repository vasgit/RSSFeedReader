package com.test.rssfeedreader.di

import com.test.rssfeedreader.utils.handlers.CoroutineHandler
import org.koin.dsl.module

val handlersModule = module {

    factory { CoroutineHandler() }

}


