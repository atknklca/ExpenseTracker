package com.example.expensetracker.repository

import com.example.expensetracker.api.CurrenyInstance
import com.example.expensetracker.model.CurrencyResponse
import retrofit2.Call

class CurrencyRepository {

    private val api by lazy { CurrenyInstance.api }

    fun getRates(base: String): Call<CurrencyResponse>{
        return api.getRates(base)
    }

}