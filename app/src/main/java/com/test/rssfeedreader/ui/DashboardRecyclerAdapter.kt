package com.test.rssfeedreader.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.test.rssfeedreader.R
import com.test.rssfeedreader.model.NewsItemModel
import com.test.rssfeedreader.utils.formatters.TimeFormatter
import kotlinx.android.synthetic.main.news_item_layout.view.*


class DashboardRecyclerAdapter(
    private var itemList: MutableList<NewsItemModel>,
    private val adapterInterface: AdapterInterface,
    private val timeFormatter: TimeFormatter
) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.titleTv
        var pubDate: TextView = itemView.dateTv
        var source: TextView = itemView.sourceTv
        var description: TextView = itemView.descriptionTv
        var container: ConstraintLayout = itemView.containerLl
        var openInBrowser: ImageView = itemView.openInBrowserIv
        var hideItem: ImageView = itemView.hideItemIv
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        val isRead = item.isRead
        val isBaned = item.isBaned
        val isShowDescription = item.isShowDescription

        val titleColorInt = if (isRead || isBaned) R.color.grey else R.color.purple_500
        val bodyColorInt = if (isBaned) R.color.grey else R.color.black

        with(holder) {
            title.text = item.title
            pubDate.text = timeFormatter.pubDateToPrettyFormat(item.pubDate)
            source.text = item.sourceTitle
            description.text = item.description

            val titleColor = ContextCompat.getColor(title.context, titleColorInt)
            title.setTextColor(titleColor)

            val bodyColor = ContextCompat.getColor(pubDate.context, bodyColorInt)
            pubDate.setTextColor(bodyColor)
            source.setTextColor(bodyColor)

            hideItem.isVisible = !adapterInterface.isAllDataShowed()
            openInBrowser.isVisible = !isBaned

            if (isShowDescription) {
                description.isVisible = true
            } else {
                description.isGone = true
            }
        }

        holder.container.setOnClickListener {
            adapterInterface.onClickItem(position)
        }
        holder.openInBrowser.setOnClickListener {
            adapterInterface.onClickOpenLink(position)
        }
        holder.hideItem.setOnClickListener {
            adapterInterface.onClickHideItem(position)
        }
    }


    override fun getItemCount() = itemList.size


    interface AdapterInterface {

        fun onClickItem(position: Int)

        fun onClickOpenLink(position: Int)

        fun onClickHideItem(position: Int)

        fun isAllDataShowed():Boolean

    }


}

