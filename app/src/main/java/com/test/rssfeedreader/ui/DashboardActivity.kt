package com.test.rssfeedreader.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.rssfeedreader.R
import com.test.rssfeedreader.utils.formatters.TimeFormatter
import com.test.rssfeedreader.model.NewsItemModel
import com.test.rssfeedreader.ui.base.mvp.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DashboardActivity : BaseActivity<DashboardPresenter>(), DashboardContract.View,
    DashboardRecyclerAdapter.AdapterInterface {


    companion object;


    override val mPresenter: DashboardPresenter by inject{ parametersOf(this) }


    private val mTimeFormatter: TimeFormatter by inject()
    private lateinit var mListData : MutableList<NewsItemModel>
    private lateinit var mAdapter : DashboardRecyclerAdapter
    private var isAllDataShow = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButtons()
        initSwipeRefresh()
        reloadData()
    }


    private fun initButtons() {
        newsBtn.setOnClickListener {
            isAllDataShow = false
            reloadData()
        }
        addDataBtn.setOnClickListener {
            isAllDataShow = true
            reloadData()
        }
    }


    private fun initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            reloadData()
        }
    }


    private fun reloadData() {
        initRV()
        mPresenter.startLoadRssByIndex(isAllDataShow)
        progressBar.isVisible = true
    }


    private fun initRV() {
        mListData = mutableListOf()
        mAdapter = DashboardRecyclerAdapter(mListData, this, mTimeFormatter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }


    override fun addData(list: List<NewsItemModel>) {
        mListData.addAll(list)
        mListData.sortByDescending {
            mTimeFormatter.pubDateToDate(it.pubDate)
        }
        mAdapter.notifyDataSetChanged()
    }


    override fun onClickItem(position: Int) {
        mListData[position].isShowDescription = !mListData[position].isShowDescription
        makeItemIsRead(position)
        mAdapter.notifyItemChanged(position)
    }


    override fun onClickOpenLink(position: Int) {
        recyclerView.stopScroll()
        openLink(mListData[position].link)
        makeItemIsRead(position)
        mAdapter.notifyItemChanged(position)
    }


    override fun onClickHideItem(position: Int) {
        recyclerView.stopScroll()

        val item = mListData[position]
        item.isBaned = true
        mPresenter.updateItem(item)

        mListData.removeAt(position)
        mAdapter.notifyDataSetChanged()
    }


    override fun hideProgressBars() {
        swipeRefreshLayout.isRefreshing = false
        progressBar.isGone = true
    }


    override fun isAllDataShowed(): Boolean = isAllDataShow


    private fun makeItemIsRead(position: Int) {
        if (!mListData[position].isRead) {
            mListData[position].isRead = true
            mPresenter.updateItem(mListData[position])
        }
    }


    private fun openLink(link: String) {
        val uri = link.toUri()
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = uri
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }


}