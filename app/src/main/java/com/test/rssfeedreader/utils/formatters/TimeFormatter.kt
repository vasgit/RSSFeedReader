package com.test.rssfeedreader.utils.formatters

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter(locale: Locale) {


    private var mLocale: Locale = Locale.getDefault()

    private val mNewsPublicationDateInputFormatter: SimpleDateFormat = SimpleDateFormat(
        "EEE, dd MMM yyyy HH:mm:ss zzz",
        Locale.ENGLISH
    )

    private val mNewsItemPublicationDatePrettyFormat: SimpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.ENGLISH
    )



    private lateinit var mDateFormatter: SimpleDateFormat
    private lateinit var mTradeHistoryTimeFormatter: SimpleDateFormat

    private lateinit var mCalendar: Calendar




    init {
        setLocale(locale)
    }


    fun setLocale(locale: Locale) {
        mDateFormatter = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            locale
        )
        mTradeHistoryTimeFormatter = SimpleDateFormat(
            "MMM d, HH:mm",
            locale
        )

        mCalendar = Calendar.getInstance(locale)
        mLocale = locale
    }


    fun pubDateToDate(publicationDate: String): Date {
        return try {
            mNewsPublicationDateInputFormatter.parse(publicationDate)
                ?: Date("01.01.1980")
        } catch(exception: ParseException) {
            Date("01.01.1980")
        }
    }


    fun pubDateToPrettyFormat(date: String): String {
        return mNewsItemPublicationDatePrettyFormat.format(pubDateToDate(date))
    }


}