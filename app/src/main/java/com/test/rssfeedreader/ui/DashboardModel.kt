package com.test.rssfeedreader.ui

import com.test.rssfeedreader.api.model.parameters.NewsParameters
import com.test.rssfeedreader.data.repositories.NewsRepository
import com.test.rssfeedreader.model.NewsItemModel
import com.test.rssfeedreader.ui.DashboardModel.ActionListener
import com.test.rssfeedreader.ui.base.mvp.model.BaseModel
import com.test.rssfeedreader.utils.extensions.log

class DashboardModel(
    private val newsRepository: NewsRepository
) : BaseModel<ActionListener>() {


    companion object {

        const val REQUEST_TYPE_NEWS = 0
        const val REQUEST_TYPE_UPDATE_ITEM = 1

    }




    fun getNewsItems(parameters: NewsParameters) {
        performRequest(
            requestType = REQUEST_TYPE_NEWS,
            params = parameters
        )
    }


    fun updateItem(item: NewsItemModel) {
        performRequest(
            requestType = REQUEST_TYPE_UPDATE_ITEM,
            params = item
        )
    }


    override suspend fun getRequestRepositoryResult(requestType: Int, params: Any): Any? {
        return when(requestType) {

            REQUEST_TYPE_NEWS -> {
                newsRepository.getNewsList(params as NewsParameters).apply {
                    log("newsRepository.getNewsList($params)")
                }
            }

            REQUEST_TYPE_UPDATE_ITEM -> {
                newsRepository.update(params as NewsItemModel).apply {
                    log("newsRepository.update($params)")
                }
            }

            else -> throw IllegalStateException()

        }
    }


    interface ActionListener : BaseActionListener


}