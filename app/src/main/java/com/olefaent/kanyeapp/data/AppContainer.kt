package com.olefaent.kanyeapp.data

import com.olefaent.kanyeapp.network.KanyeApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val kanyeRepository: KanyeRepository
}

class DefaultAppContainer: AppContainer{
    private val base_url = "https://api.kanye.rest/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val retrofitService: KanyeApiService by lazy {
        retrofit.create(KanyeApiService::class.java)
    }

    override val kanyeRepository: KanyeRepository by lazy {
        NetworkKanyeRepository(retrofitService)
    }

}