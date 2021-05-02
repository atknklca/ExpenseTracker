package com.example.expensetracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.SharedPreferencesManager
import com.example.expensetracker.model.CurrencyResponse
import com.example.expensetracker.model.Rates
import com.example.expensetracker.repository.CurrencyRepository
import com.example.expensetracker.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CurrencyViewModel(application: Application): AndroidViewModel(application) {

    private val lastRates: MutableLiveData<CurrencyResponse> = MutableLiveData()
    private val repository by lazy { CurrencyRepository() }

    val sharedPref = SharedPreferencesManager.getSharedPref(application)

    fun getRates(base: String): MutableLiveData<CurrencyResponse>{

        viewModelScope.launch {
            repository.getRates(base).enqueue(object : retrofit2.Callback<CurrencyResponse>{
                override fun onResponse(
                    call: Call<CurrencyResponse>,
                    response: Response<CurrencyResponse>
                ){
                    if (response.isSuccessful){
                        saveLatestRates(response)
                        lastRates.value = response.body()
                    }
                }

                override fun onFailure(call: Call<CurrencyResponse>, T: Throwable){
                    getDefaultRates(base)
                }
            })
        }
        return lastRates
    }

    //Api'den gelen son verilerin saklanması.
    private fun saveLatestRates(response:Response<CurrencyResponse> ) {
        sharedPref?.edit()?.putFloat("TRY_TO_EUR", response.body()!!.rates.USD.toFloat())?.apply()
        sharedPref?.edit()?.putFloat("GBP_TO_EUR", response.body()!!.rates.EUR.toFloat())?.apply()
        sharedPref?.edit()?.putFloat("USD_TO_EUR", response.body()!!.rates.GBP.toFloat())?.apply()
    }

    //Uygulamanın ilk açılışı da dahil olmak üzere internet bağlantısı gerçekleşmemişse ya da veri api'den gelmemişse.
    // Senaryo 1 : İlk açılışta internet bağlantısı yoksa veriler benim default olarak girdiğim sonuçlardan elde edilir.
    // Senaryo 2: Eğer ilk açılışta herhangi bir bağlantı sağlanmışsa veriler api'den temin edilir ve son güncel kur saklanır.
    private fun getDefaultRates(base: String){
        val usd = sharedPref?.getFloat("TRY_TO_EUR", Constants.TRY_TO_EUR) //veri yoksa defalult değer Constants'da
        val eur = sharedPref?.getFloat("GBP_TO_EUR", Constants.GBP_TO_EUR)
        val gbp = sharedPref?.getFloat("USD_TO_EUR", Constants.USD_TO_EUR)
        lastRates.value = CurrencyResponse(base, Rates(eur!!.toDouble(),gbp!!.toDouble(),0.0,usd!!.toDouble()))
    }

}

