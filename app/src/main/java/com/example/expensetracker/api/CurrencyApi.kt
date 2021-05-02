package com.example.expensetracker.api

import retrofit2.http.GET

interface CurrencyApi {

    @GET("/api/v7/convert?")
    suspend fun getPost():Post

}