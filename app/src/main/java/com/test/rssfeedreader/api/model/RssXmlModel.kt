package com.test.rssfeedreader.api.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssXmlModel(

    @field:Element(name="title", data = true)
    @param:Element(name = "title", data = true)
    @field:Path("channel")
    val title: String,

    @param:ElementList(name = "item", inline = true)
    @field:ElementList(name = "item", inline = true)
    @field:Path("channel")
    val rssItems: List<NewsItemXmlModel>

)