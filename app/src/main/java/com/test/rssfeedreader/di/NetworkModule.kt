package com.test.rssfeedreader.di

import com.test.rssfeedreader.di.utils.get
import com.test.rssfeedreader.di.utils.single
import com.test.rssfeedreader.BuildConfig
import com.test.rssfeedreader.api.RssApi
import com.test.rssfeedreader.api.services.RssService
import com.test.rssfeedreader.api.utils.ResponseExtractor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit


const val OK_HTTP_CLIENT_RSS = "ok_http_client_rss"
const val OK_HTTP_CLIENT_TIMEOUT = 4_000L

private const val RETROFIT_RSS = "retrofit_rss"
private const val RSS_BASE_URL = "http://feeds.bbci.co.uk/"


val apiModule = module {

    single(OK_HTTP_CLIENT_RSS) {
        OkHttpClient.Builder().apply {
            connectTimeout(OK_HTTP_CLIENT_TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(OK_HTTP_CLIENT_TIMEOUT, TimeUnit.MILLISECONDS)

            if(BuildConfig.DEBUG) {
                addInterceptor(get<HttpLoggingInterceptor>())
            }
        }.build()
    }

    single(RETROFIT_RSS) {
        Retrofit.Builder()
            .baseUrl(RSS_BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create(Persister(AnnotationStrategy())))
            .client(get(OK_HTTP_CLIENT_RSS))
            .build()
    }

    single { get<Retrofit>(RETROFIT_RSS).create(RssService::class.java) }

    single { RssApi(get(), get()) }

    single { ResponseExtractor() }


    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}
