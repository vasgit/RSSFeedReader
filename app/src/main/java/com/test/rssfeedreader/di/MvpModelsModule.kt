package com.test.rssfeedreader.di

import com.test.rssfeedreader.ui.DashboardModel
import org.koin.dsl.module

val mvpModelsModule = module {

    factory { DashboardModel(get()) }

}


