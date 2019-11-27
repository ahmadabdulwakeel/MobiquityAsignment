package com.mobiquity.infrastructure.networks

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.mobiquity.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val networkModule = module {

    single {OkHttpClient.Builder()}
    single<Retrofit> {
        val httpClient=get<OkHttpClient.Builder>()

        val logger = HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        httpClient.addInterceptor(logger)

        val namingsForCSharp=GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create()
        val retroBuilder =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(namingsForCSharp))
                .addConverterFactory(ScalarsConverterFactory.create())


        retroBuilder.client(httpClient.build())
        retroBuilder.build()
    }



}

