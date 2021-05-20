package com.test.rssfeedreader.ui

import com.test.rssfeedreader.model.NewsItemModel
import com.test.rssfeedreader.ui.base.mvp.views.BaseView

interface DashboardContract {


    interface View: BaseView {

        fun addData(list : List<NewsItemModel>)

        fun hideProgressBars()

    }


    interface ActionListener {

        fun updateItem(item: NewsItemModel)

    }


}