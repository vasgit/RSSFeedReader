package com.test.rssfeedreader.di

import com.test.rssfeedreader.ui.DashboardContract
import com.test.rssfeedreader.ui.DashboardPresenter
import org.koin.dsl.module

val mvpPresentersModule = module {

    factory { (view: DashboardContract.View) -> DashboardPresenter(view, get(), get()) }

}



