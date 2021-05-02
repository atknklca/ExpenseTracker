package com.example.expensetracker.api

import com.example.expensetracker.model.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/latest")
     fun getRates(
        @Query("base") base: String
    ): Call<CurrencyResponse>

}