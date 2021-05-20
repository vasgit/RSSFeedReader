package com.test.rssfeedreader.ui

import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.utils.formatters.TimeFormatter
import com.test.rssfeedreader.model.NewsItemModel
import com.test.rssfeedreader.ui.base.mvp.presenters.BasePresenter

class DashboardPresenter(
    view: DashboardContract.View,
    model: DashboardModel,
    private val timeFormatter: TimeFormatter
) : BasePresenter<DashboardContract.View, DashboardModel>(view, model),
    DashboardContract.ActionListener, DashboardModel.ActionListener {


    val rssList = listOf(
        "http://feeds.bbci.co.uk/news/rss.xml?edition=uk",
        "http://feeds.bbci.co.uk/news/world/rss.xml?edition=uk",
        "http://feeds.bbci.co.uk/news/england/rss.xml?edition=uk"
    )

    private var rssListPosition = 0
    private var isAllDataShow = false




    init {
        model.setActionListener(this)
    }


    fun startLoadRssByIndex(allData: Boolean) {
        rssListPosition = 0
        isAllDataShow = allData
        loadRssIndex()
    }


    private fun loadRssIndex() {
        if (rssListPosition < rssList.size) {
            mModel.getNewsItems(NewsParameters(
                link = rssList[rssListPosition],
                loadAllData = isAllDataShow)
            )
        }
    }


    override fun updateItem(item: NewsItemModel) {
        mModel.updateItem(item)
    }


    override fun onResponseReceived(requestType: Int) {
        when(requestType) {
            DashboardModel.REQUEST_TYPE_NEWS -> {
                rssListPosition += 1
                loadRssIndex()
                mView.hideProgressBars()
            }
        }
    }


    override fun onRequestSucceeded(requestType: Int, response: Any, metadata: Any) {
        when(requestType) {
            DashboardModel.REQUEST_TYPE_NEWS -> {
                onGetNewsSucceeded(response as List<NewsItemModel>)
            }
        }
    }


    private fun onGetNewsSucceeded(list: List<NewsItemModel>) {
        mView.addData(list)
    }


}