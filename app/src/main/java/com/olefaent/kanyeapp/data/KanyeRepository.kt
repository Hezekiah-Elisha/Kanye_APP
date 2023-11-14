package com.olefaent.kanyeapp.data

import com.olefaent.kanyeapp.model.Kanye
import com.olefaent.kanyeapp.network.KanyeApiService

interface KanyeRepository{
    /**
     * Gets a quote from the network
     */
    suspend fun getQuote(): Kanye
}

class NetworkKanyeRepository(
    private val kanyeApiService: KanyeApiService
): KanyeRepository {
    override suspend fun getQuote(): Kanye = kanyeApiService.getQuote()
}