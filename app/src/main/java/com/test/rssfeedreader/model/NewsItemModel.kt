package com.test.rssfeedreader.model

data class NewsItemModel(
    val id: String,
    val title: String,
    val link: String,
    val pubDate: String,
    val sourceTitle: String,
    val sourceLink: String,
    val description: String,
    var isRead:Boolean,
    var isBaned:Boolean,
    var isShowDescription:Boolean
)