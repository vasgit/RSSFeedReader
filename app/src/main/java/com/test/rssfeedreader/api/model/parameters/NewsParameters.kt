package com.test.rssfeedreader.api.model.parameters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsParameters(
    val link: String,
    val loadAllData: Boolean
) : Parcelable