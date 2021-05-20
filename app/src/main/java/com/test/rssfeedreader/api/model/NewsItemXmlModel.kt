package com.test.rssfeedreader.api.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class NewsItemXmlModel(
    @field:Element(name = "title", data = true)
    @param:Element(name = "title", data = true)
    val title: String,

    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String,

    @field:Element(name = "pubDate")
    @param:Element(name = "pubDate")
    val pubDate: String,

    @field:Element(name = "description")
    @param:Element(name = "description")
    val description: String
) {


    val id = "$title $pubDate"


}