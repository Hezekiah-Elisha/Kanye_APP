package com.olefaent.kanyeapp.network

import com.olefaent.kanyeapp.model.Kanye
import retrofit2.http.GET

interface KanyeApiService {
    @GET("/")
    suspend fun getQuote(): Kanye
}